package br.rio.puc.lac.android.course.statusbar;

import android.app.Activity;
import android.app.Notification;
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
    String ns = Context.NOTIFICATION_SERVICE;
    NotificationManager mNotificationManager =
      (NotificationManager) getSystemService(ns);

    int icon = R.drawable.ic_launcher;
    CharSequence tickerText = "Esta é uma notificação de status bar";
    long when = System.currentTimeMillis();

    Notification notification = new Notification(icon, tickerText, when);

    Context context = getApplicationContext();
    CharSequence contentTitle = "Título";
    CharSequence contentText = "Conteúdo da notificação...";
    Intent notificationIntent = new Intent(this, StatusBarActivity.class);
    PendingIntent contentIntent =
      PendingIntent.getActivity(this, 0, notificationIntent, 0);

    notification.setLatestEventInfo(context, contentTitle, contentText,
      contentIntent);

    mNotificationManager.notify(HELLO_ID, notification);
  }
}