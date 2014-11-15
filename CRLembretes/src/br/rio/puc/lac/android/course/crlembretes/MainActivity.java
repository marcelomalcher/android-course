package br.rio.puc.lac.android.course.crlembretes;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Cursor lembretes =
      getContentResolver().query(LembretesContract.CONTENT_URI, null, null,
        null, null);

    Toast.makeText(this, LembretesContract.CONTENT_URI.toString(), 3000).show();

    String[] from = { LembretesContract.Lembrete.COLUMN_SUMMARY };
    int[] to = { android.R.id.text1 };

    SimpleCursorAdapter adapter =
      new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
        lembretes, from, to);

    setListAdapter(adapter);
  }
}