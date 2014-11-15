package br.rio.puc.lac.android.course.boundservicemessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {

  static final String MSG_SAY_HELLO_EXTRA_KEY = "helloKey";
  static final int MSG_SAY_HELLO = 1;

  final Messenger mMessenger;
  {
    mMessenger = new Messenger(new Handler() {
      @Override
      public void handleMessage(Message msg) {
        switch (msg.what) {
          case MSG_SAY_HELLO:
            Toast.makeText(getApplicationContext(),
              "hello! " + msg.getData().getString(MSG_SAY_HELLO_EXTRA_KEY),
              Toast.LENGTH_SHORT).show();
            break;
          default:
            super.handleMessage(msg);
        }
      }

    });
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mMessenger.getBinder();
  }
}