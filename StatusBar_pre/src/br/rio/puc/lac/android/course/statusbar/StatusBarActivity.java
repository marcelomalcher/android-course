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
    //- Receba a refer�ncia a NotificationManager a partir do m�todo getSystemService(Context.NOTIFICATION_SERVICE)
    //- Para criar uma notifica��o ser� passado como par�metro um inteiro (�cone), o texto a ser mostrado na barra e a hora a ser mostrada
    //--- Para �cone, receba a refer�ncia ao �cone da aplica��o R.drawable.ic_launcher
    //--- Para hora a ser mostrada, receba System.currentTimeMillis()
    //- Instancie a notifica��o
    //- Perceba a cria��o de um intent e de um PendingIntent
    //--- O PendingIntent ser� passado para a notifica��o e conter� o Intent criado
    //- Utilize o m�todo setLatestEventInfo() da notifica��o para definir os par�metros
    //--- Contexto da notifica��o (utilize o contexto da aplica��o - context)
    //--- T�tulo da notifica��o (contentTitle)
    //--- Texto da notifica��o (contentText)
    //--- PendingIntent (contentIntent)

    Context context = getApplicationContext();
    CharSequence contentTitle = "T�tulo";
    CharSequence contentText = "Conte�do da notifica��o...";

    Intent notificationIntent = new Intent(this, StatusBarActivity.class);
    PendingIntent contentIntent =
      PendingIntent.getActivity(this, 0, notificationIntent, 0);

    //- Utilize o NotificationManager para notificar o usu�rio com o m�todo notify(HELLO_ID, notification)  
  }
}