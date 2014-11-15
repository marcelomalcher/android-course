package br.rio.puc.lac.android.course.crcontatos;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Cursor people =
      getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,
        null, null, null);

    Toast
      .makeText(this, ContactsContract.Contacts.CONTENT_URI.toString(), 3000)
      .show();

    String[] from = { ContactsContract.Contacts.DISPLAY_NAME };
    int[] to = { android.R.id.text1 };

    SimpleCursorAdapter adapter =
      new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
        people, from, to);

    setListAdapter(adapter);
  }
}