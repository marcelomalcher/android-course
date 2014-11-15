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

    //Criando objeto queryBuilder - auxilia na cria��o da query
    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    //Chamada do m�todo checkColumns (privado) para verificar se as colunas solicitadas
    //no par�metro projection correspondem �s colunas dispon�veis na tabela de lembretes
    checkColumns(projection);

    //Definindo qual tabela ser� pesquisada - no caso, a tabela de lembretes      
    queryBuilder.setTables(LembreteTable.TABLE_TODO);

    Cursor cursor = null;

    //EXERC�CIO
    //- Fazer a verifica��o da URI recebida comparando-a com os valores associados via UriMatcher
    //--- Caso a Uri contenha o identificador, manipular o objeto queryBuilder para que condicone a 
    //    pesquisa para retornar o lembrete com este identificador
    //- Receber ent�o objeto database para leitura e executar a query utilizando queryBuilder que 
    //  retornar� um objeto Cursor, declarado acima    

    cursor.setNotificationUri(getContext().getContentResolver(), uri);
    return cursor;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    //EXERC�CIO - m�todo insert
    //- Este m�todo deve permitir criar um novo lembrete na base de dados
    //- Recupere o objeto para escrita de banco de dados e insira o lembrete com os valores definidos
    //- N�o esque�a de tratar a Uri passada - a Uri deve especificar lembretes em geral
    //--- caso contr�rio, se especificar um lembrete espec�fico, ou uma Uri diferente, lan�ar exce��o

    getContext().getContentResolver().notifyChange(uri, null);

    //EXERC�CIO - Retorne a Uri declarada para este lembrete rec�m criado    
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

    //EXERC�CIO
    //- Tratar a Uri para definir quais lembretes ser�o atualizados
    //- N�o esque�a de utilizar os valores e sele��es passados como par�metros 

    getContext().getContentResolver().notifyChange(uri, null);

    //EXERC�CIO - retorna n�mero de registros atualizados
  }

  /**
   * Checa se as colunas passadas como par�meteros pertencem a tabela de
   * embretes
   * 
   * @param projection
   */
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