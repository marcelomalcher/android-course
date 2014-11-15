package br.rio.puc.lac.android.course.adapterlist;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

  @Override
  public void onCreate(Bundle context) {
    super.onCreate(context);
    ExemploArrayAdapter adapter =
      new ExemploArrayAdapter(this, getResources().getStringArray(
        R.array.paises));
    setListAdapter(adapter);
  }
}