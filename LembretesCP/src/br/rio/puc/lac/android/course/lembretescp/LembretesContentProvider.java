package br.rio.puc.lac.android.course.lembretescp;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class LembretesContentProvider extends ContentProvider {

  private LembretesDatabaseHelper database;

  private static final int LEMBRETES = 10;
  private static final int UNICO_LEMBRETE = 20;

  private static final String AUTHORITY =
    "br.rio.puc.lac.android.course.lembretescp";
  private static final String BASE_PATH = "lembretes";

  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
    + "/" + BASE_PATH);

  public static final String CONTENT_TYPE =
    ContentResolver.CURSOR_DIR_BASE_TYPE + "/lembretes";
  public static final String CONTENT_ITEM_TYPE =
    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/lembrete";

  private static final UriMatcher sURIMatcher = new UriMatcher(
    UriMatcher.NO_MATCH);
  static {
    sURIMatcher.addURI(AUTHORITY, BASE_PATH, LEMBRETES);
    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", UNICO_LEMBRETE);
  }

  @Override
  public boolean onCreate() {
    database = new LembretesDatabaseHelper(getContext());
    return false;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
    String[] selectionArgs, String sortOrder) {

    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    checkColumns(projection);

    queryBuilder.setTables(LembreteTable.TABLE_TODO);

    int uriType = sURIMatcher.match(uri);
    switch (uriType) {
      case LEMBRETES:
        break;
      case UNICO_LEMBRETE:

        queryBuilder.appendWhere(LembreteTable.COLUMN_ID + "="
          + uri.getLastPathSegment());
        break;
      default:
        throw new IllegalArgumentException("URI desconhecida: " + uri);
    }

    SQLiteDatabase db = database.getWritableDatabase();
    Cursor cursor =
      queryBuilder.query(db, projection, selection, selectionArgs, null, null,
        sortOrder);

    cursor.setNotificationUri(getContext().getContentResolver(), uri);

    return cursor;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    long id = 0;
    switch (uriType) {
      case LEMBRETES:
        id = sqlDB.insert(LembreteTable.TABLE_TODO, null, values);
        break;
      default:
        throw new IllegalArgumentException("URI desconhecida: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return Uri.parse(BASE_PATH + "/" + id);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsDeleted = 0;
    switch (uriType) {
      case LEMBRETES:
        rowsDeleted =
          sqlDB.delete(LembreteTable.TABLE_TODO, selection, selectionArgs);
        break;
      case UNICO_LEMBRETE:
        String id = uri.getLastPathSegment();
        if (TextUtils.isEmpty(selection)) {
          rowsDeleted =
            sqlDB.delete(LembreteTable.TABLE_TODO, LembreteTable.COLUMN_ID
              + "=" + id, null);
        }
        else {
          rowsDeleted =
            sqlDB.delete(LembreteTable.TABLE_TODO, LembreteTable.COLUMN_ID
              + "=" + id + " and " + selection, selectionArgs);
        }
        break;
      default:
        throw new IllegalArgumentException("URI desconhecida: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsDeleted;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection,
    String[] selectionArgs) {

    int uriType = sURIMatcher.match(uri);
    SQLiteDatabase sqlDB = database.getWritableDatabase();
    int rowsUpdated = 0;
    switch (uriType) {
      case LEMBRETES:
        rowsUpdated =
          sqlDB.update(LembreteTable.TABLE_TODO, values, selection,
            selectionArgs);
        break;
      case UNICO_LEMBRETE:
        String id = uri.getLastPathSegment();
        if (TextUtils.isEmpty(selection)) {
          rowsUpdated =
            sqlDB.update(LembreteTable.TABLE_TODO, values,
              LembreteTable.COLUMN_ID + "=" + id, null);
        }
        else {
          rowsUpdated =
            sqlDB.update(LembreteTable.TABLE_TODO, values,
              LembreteTable.COLUMN_ID + "=" + id + " and " + selection,
              selectionArgs);
        }
        break;
      default:
        throw new IllegalArgumentException("URI desconhecida: " + uri);
    }
    getContext().getContentResolver().notifyChange(uri, null);
    return rowsUpdated;
  }

  private void checkColumns(String[] projection) {
    String[] available =
      { LembreteTable.COLUMN_CATEGORY, LembreteTable.COLUMN_SUMMARY,
          LembreteTable.COLUMN_DESCRIPTION, LembreteTable.COLUMN_ID };
    if (projection != null) {
      HashSet<String> requestedColumns =
        new HashSet<String>(Arrays.asList(projection));
      HashSet<String> availableColumns =
        new HashSet<String>(Arrays.asList(available));

      if (!availableColumns.containsAll(requestedColumns)) {
        throw new IllegalArgumentException("Colunas desconhecidas na proje��o");
      }
    }
  }

}