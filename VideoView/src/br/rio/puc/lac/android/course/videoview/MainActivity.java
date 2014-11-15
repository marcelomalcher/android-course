package br.rio.puc.lac.android.course.videoview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    //TAREFA 
    //-Receber objeto VideoView definido na interface
    //-Definir caminho para arquivo como "/sdcard/vid.mp4" (este arquivo deve estar lá - colocar via DDMS)
    //-Definir o controle da mídia instanciando novo objeto MediaController
    //-Iniciar mídia e requisitar foco
  }
}