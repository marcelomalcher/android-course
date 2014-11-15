package br.rio.puc.lac.android.course.statusbar;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatusBarActivity extends Activity {

  private static final int HELLO_ID = 1;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View v) {
    NotificationManager mNotificationManager;

    //TAREFA
    //- Receba a referência a NotificationManager a partir do método getSystemService(Context.NOTIFICATION_SERVICE)
    //- Para criar uma notificação será passado como parâmetro um inteiro (ícone), o texto a ser mostrado na barra e a hora a ser mostrada
    //--- Para ícone, receba a referência ao ícone da aplicação R.drawable.ic_launcher
    //--- Para hora a ser mostrada, receba System.currentTimeMillis()
    //- Instancie a notificação
    //- Perceba a criação de um intent e de um PendingIntent
    //--- O PendingIntent será passado para a notificação e conterá o Intent criado
    //- Utilize o método setLatestEventInfo() da notificação para definir os parâmetros
    //--- Contexto da notificação (utilize o contexto da aplicação - context)
    //--- Título da notificação (contentTitle)
    //--- Texto da notificação (contentText)
    //--- PendingIntent (contentIntent)

    Context context = getApplicationContext();
    CharSequence contentTitle = "Título";
    CharSequence contentText = "Conteúdo da notificação...";

    Intent notificationIntent = new Intent(this, StatusBarActivity.class);
    PendingIntent contentIntent =
      PendingIntent.getActivity(this, 0, notificationIntent, 0);

    //- Utilize o NotificationManager para notificar o usuário com o método notify(HELLO_ID, notification)  
  }
}