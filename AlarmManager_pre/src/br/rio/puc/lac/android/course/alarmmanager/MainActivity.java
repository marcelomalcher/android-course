package br.rio.puc.lac.android.course.alarmmanager;

import android.app.Activity;
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
      pendingIntent = PendingIntent.getService(MainActivity.this, 0, i, 0);
      //TAREFA 
      //- Receba a instância de AlarmManager via getSystemService(Context.ALARM_SERVICE)
      //- Receba a instância de Calendar e adicione 10 segundos
      //- Defina que o alarmManager deve disparar no tipo RTC_WAKEUP, na hora do calendário e executar o pendingIntent definido
      Toast.makeText(MainActivity.this,
        "Alarme definido para daqui a 10 segundos", Toast.LENGTH_LONG).show();
    }
    else if (v.getId() == R.id.cancelalarm) {
      //TAREFA
      //- Receba a instância de AlarmManager via getSystemService(Context.ALARM_SERVICE)
      //- Cancele o alarme com o método cancel() passando o pendingIntent já existente
      Toast.makeText(MainActivity.this, "Cancelado!", Toast.LENGTH_LONG).show();
    }
  }
}