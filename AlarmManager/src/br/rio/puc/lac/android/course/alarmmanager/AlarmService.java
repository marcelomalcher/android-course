package br.rio.puc.lac.android.course.alarmmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmService extends Service {

  @Override
  public void onCreate() {
    super.onCreate();
    Toast.makeText(this, "AlarmService criado", Toast.LENGTH_LONG).show();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Toast.makeText(this, "AlarmService destru�do", Toast.LENGTH_LONG).show();

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Toast
      .makeText(this, "Comando de in�cio do AlarmService", Toast.LENGTH_LONG)
      .show();
    return super.onStartCommand(intent, flags, startId);
  }
}