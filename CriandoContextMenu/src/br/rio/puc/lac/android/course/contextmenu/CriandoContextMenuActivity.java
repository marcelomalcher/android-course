package br.rio.puc.lac.android.course.contextmenu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class CriandoContextMenuActivity extends ListActivity {

  private String[] paises;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    paises = getResources().getStringArray(R.array.paises);

    ArrayAdapter<String> adapter =
      new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
        paises);

    setListAdapter(adapter);

    registerForContextMenu(getListView());
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
    ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    if (v == getListView()) {
      MenuInflater inflater = getMenuInflater();
      inflater.inflate(R.menu.context, menu);
    }

  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    switch (item.getItemId()) {
      case R.id.editar:
        Toast.makeText(CriandoContextMenuActivity.this,
          "Clicou em EDITAR: " + paises[info.position], 3000).show();
        return true;
      case R.id.share:
        Toast.makeText(CriandoContextMenuActivity.this,
          "Clicou em COMPARTILHAR: " + paises[info.position], 3000).show();
        return true;
      default:
        return super.onContextItemSelected(item);
    }
  }

}