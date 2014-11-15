package br.rio.puc.lac.android.course.receiverservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

// Fazer esta classe ser um IntentService
public class ReceiverService {

  public static final String TIME_KEY = "TimeKey";

  private static final long SLEEP = 10000;

  private static final String TAG = ReceiverService.class.getName();

  private String createdTime;

  public ReceiverService() {
    super("ReceiverService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Log.d(TAG, "Service iniciou");

    createdTime = intent.getExtras().getString(TIME_KEY);

    Notification notification =
      new Notification(R.drawable.ic_launcher, "Service",
        System.currentTimeMillis());

    Intent notificationIntent = new Intent(this, ShowTimeActivity.class);
    notificationIntent.putExtra(TIME_KEY, createdTime);

    PendingIntent pendingIntent =
      PendingIntent.getActivity(this, 0, notificationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT);

    notification.setLatestEventInfo(this, "Detalhes", "Notificação",
      pendingIntent);

    //Colocar o service em foreground utilizando o identificador e a notificação criada

    try {
      Thread.sleep(SLEEP);

      //Tirar o service de foreground 
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
