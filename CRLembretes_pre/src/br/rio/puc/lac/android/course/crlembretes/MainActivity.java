package br.rio.puc.lac.android.course.crlembretes;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //EXERCÍCIO
    // - Conectar ao ContentProvider de Lembretes e recuperar os lembretes existentes
    //   através do método query
    // - Associar o cursor retornado para um novo adapter para esta ListActivity
  }
}