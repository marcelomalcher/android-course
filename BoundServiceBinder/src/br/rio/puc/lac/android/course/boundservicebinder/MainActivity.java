package br.rio.puc.lac.android.course.boundservicebinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;
import br.rio.puc.lac.android.course.boundservicebinder.LocalService.LocalBinder;

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
    Intent intent = new Intent(this, LocalService.class);
    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (mBound) {
      unbindService(mConnection);
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
      LocalBinder binder = (LocalBinder) service;
      mService = binder.getService();
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