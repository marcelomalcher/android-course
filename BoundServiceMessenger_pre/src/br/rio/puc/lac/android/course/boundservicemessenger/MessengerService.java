package br.rio.puc.lac.android.course.boundservicemessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Messenger;

public class MessengerService extends Service {

  static final String MSG_SAY_HELLO_EXTRA_KEY = "helloKey";
  static final int MSG_SAY_HELLO = 1;

  final Messenger mMessenger;
  {
    mMessenger = new Messenger(new Handler() {
      //TAREFA 
      //- Implementar m�todo handleMessage
      //--Neste m�todo, dever� checar se mensagem � do tipo (what) MSG_SAY_HELLO
      //--Se sim, dever� extrair o objeto Bundle da mensagem (getData()) e recuperar a String identificada pela chave MSG_SAY_HELLO_EXTRA_KEY
      //--Mostrar o valor desta String em uma notifica��o Toast
    });
  }

  @Override
  public IBinder onBind(Intent intent) {
    //TAREFA - Retornar binder do objeto mMessenger
  }
}