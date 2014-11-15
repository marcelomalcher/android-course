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
    //- Recuperar instância de ConnectivityManager 
    //- A partir da instância, recuperar informações da interface de rede ativa
    //- Mostrar nome da interface e se está conectada ou não
    //- Recuperar informações da rede Wifi e móvel e mostrar se estão conectadas ou não
  }
}