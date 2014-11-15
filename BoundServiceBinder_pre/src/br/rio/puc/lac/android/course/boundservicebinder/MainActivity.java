package br.rio.puc.lac.android.course.boundservicebinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  LocalService mService;
  boolean mBound = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    //TAREFA - Criar intent e solicitar conexão ao Service através do método bindService() 
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (mBound) {
      //TAREFA - solicitar desconexão ao Service através do método unbindService()
      mBound = false;
    }
  }

  /**
   * Called when a button is clicked (the button in the layout file attaches to
   * this method with the android:onClick attribute)
   */
  public void onButtonClick(View v) {
    if (mBound) {
      int num = mService.getRandomNumber();
      Toast.makeText(this, "Número: " + num, Toast.LENGTH_SHORT).show();
    }
  }

  /** Defines callbacks for service binding, passed to bindService() */
  private ServiceConnection mConnection = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName className, IBinder service) {
      //TAREFA - fazer o cast do objeto IBinder para LocalBinder e receber referência ao Service
      mBound = true;
      Toast
        .makeText(MainActivity.this, "Service conectado", Toast.LENGTH_SHORT)
        .show();
    }

    @Override
    public void onServiceDisconnected(ComponentName arg0) {
      mBound = false;
      Toast.makeText(MainActivity.this, "Service desconectado",
        Toast.LENGTH_SHORT).show();
    }
  };
}