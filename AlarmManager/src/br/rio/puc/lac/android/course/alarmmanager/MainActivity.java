package br.rio.puc.lac.android.course.alarmmanager;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

  private PendingIntent pendingIntent;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Button buttonStart = (Button) findViewById(R.id.startalarm);
    Button buttonCancel = (Button) findViewById(R.id.cancelalarm);
  }

  public void onClick(View v) {
    if (v.getId() == R.id.startalarm) {
      Intent i = new Intent(MainActivity.this, AlarmService.class);
      pendingIntent =
        PendingIntent.getService(MainActivity.this, 0, i, 0);
      AlarmManager alarmManager =
        (AlarmManager) getSystemService(ALARM_SERVICE);
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.SECOND, 10);
      alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
        pendingIntent);
      Toast.makeText(MainActivity.this,
        "Alarme definido para daqui a 10 segundos", Toast.LENGTH_LONG).show();
    }
    else if (v.getId() == R.id.cancelalarm) {
      AlarmManager alarmManager =
        (AlarmManager) getSystemService(ALARM_SERVICE);
      alarmManager.cancel(pendingIntent);
      Toast.makeText(MainActivity.this, "Cancelado!", Toast.LENGTH_LONG).show();
    }
  }
}