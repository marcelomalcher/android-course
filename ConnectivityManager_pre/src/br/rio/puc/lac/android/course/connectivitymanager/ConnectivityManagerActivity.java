package br.rio.puc.lac.android.course.connectivitymanager;

import android.app.Activity;
import android.os.Bundle;

public class ConnectivityManagerActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    //TAREFA 
    //- Recuperar inst�ncia de ConnectivityManager 
    //- A partir da inst�ncia, recuperar informa��es da interface de rede ativa
    //- Mostrar nome da interface e se est� conectada ou n�o
    //- Recuperar informa��es da rede Wifi e m�vel e mostrar se est�o conectadas ou n�o
  }
}