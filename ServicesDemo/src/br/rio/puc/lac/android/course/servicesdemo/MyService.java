package br.rio.puc.lac.android.course.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
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

  @Override
  public void onDestroy() {
    Toast.makeText(this, "Service finalizou", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onDestroy");
    player.stop();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Toast.makeText(this, "Service iniciou", Toast.LENGTH_SHORT).show();
    Log.d(TAG, "onStart");
    player.start();
    return super.onStartCommand(intent, flags, startId);
  }
}