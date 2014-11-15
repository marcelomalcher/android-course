package br.rio.puc.lac.android.course.servicesdemo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

// Altere esta classe para que seja um android.app.Service
public class MyService {

  private static final String TAG = "MyService";
  MediaPlayer player;

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public void onCreate() {
    Toast.makeText(this, "Service criado", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onCreate");

    player = MediaPlayer.create(this, R.raw.musicdemo);
    player.setLooping(false); // Set looping
  }

  //Implemente os demais m�todos necess�rios: onStart() e onStop()

  //Em onStartComand(), crie uma notifica��o Toast para informar ao usu�rio e d� player.start()

  //Em onDestroy(), crie uma notifica��o Toast para informar ao usu�rio e d� player.stop()

}