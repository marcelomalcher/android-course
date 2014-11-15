package br.rio.puc.lac.android.course.boundservicemessenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

  private EditText et;

  Messenger mService = null;

  boolean mBound;

  private ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName className, IBinder service) {
      mService = new Messenger(service);
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
    Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
    Bundle data = new Bundle();
    data.putString(MessengerService.MSG_SAY_HELLO_EXTRA_KEY, et.getText()
      .toString());
    msg.setData(data);
    try {
      mService.send(msg);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }
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