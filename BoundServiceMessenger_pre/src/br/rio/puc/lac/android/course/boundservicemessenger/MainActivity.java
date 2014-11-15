package br.rio.puc.lac.android.course.boundservicemessenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

  private EditText et;

  Messenger mService = null;

  boolean mBound;

  private ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      //TAREFA - Instanciar objeto Messenger utilizando parâmetero IBinder
      mBound = true;
    }

    public void onServiceDisconnected(ComponentName className) {
      mService = null;
      mBound = false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    et = (EditText) findViewById(R.id.editText1);
  }

  public void onButtonClick(View v) {
    if (!mBound)
      return;
    //TAREFA 
    //- Criar mensagem utilizando como identificador (what) o valor de MessengerService.MSG_SAY_HELLO
    //- Criar objeto Bundle
    //- Inserir em objeto Bundle o texto inserido no widget EditText
    //- Enviar mensagem ao Service
  }

  @Override
  protected void onStart() {
    super.onStart();
    bindService(new Intent(this, MessengerService.class), mConnection,
      Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (mBound) {
      unbindService(mConnection);
      mBound = false;
    }
  }
}