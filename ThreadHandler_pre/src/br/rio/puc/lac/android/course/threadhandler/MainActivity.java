package br.rio.puc.lac.android.course.threadhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

  private Handler handler;
  private ProgressBar progress;

  /** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    progress = (ProgressBar) findViewById(R.id.progressBar1);
    handler = new Handler();
  }

  public void startProgress(View view) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i <= 10; i++) {
          final int value = i;
          try {
            Thread.sleep(2000);
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
          //TAREFA - A partir do objeto Handler, executar m�todo post para atribuir novo valor (value) para barra de progresso 
        }
      }
    };
    new Thread(runnable).start();
  }
}