package br.rio.puc.lac.android.course.mediaplayerservice;

import java.io.IOException;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.PowerManager;

public class MediaService extends Service implements
  MediaPlayer.OnPreparedListener {

  public static final String ACTION_PLAY =
    "br.rio.puc.lac.android.course.mediaservice.PLAY";
  public static final String ACTION_MEDIA_URL_KEY =
    "br.rio.puc.lac.android.course.mediaservice.MEDIA_URL_KEY";
  public static final String ACTION_MEDIA_TITLE_KEY =
    "br.rio.puc.lac.android.course.mediaservice.MEDIA_NAME_KEY";
  public static final String ACTION_MEDIA_ARTIST_KEY =
    "br.rio.puc.lac.android.course.mediaservice.MEDIA_ARTIST_KEY";

  private boolean isPlaying = false;

  private String titleName;
  private String artistName;

  MediaPlayer mMediaPlayer = null;

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent.getAction().equals(ACTION_PLAY)) {
      if (!isPlaying) {
        String url = intent.getExtras().getString(ACTION_MEDIA_URL_KEY);
        Uri myUri = Uri.parse(url);
        //
        titleName = intent.getExtras().getString(ACTION_MEDIA_TITLE_KEY);
        artistName = intent.getExtras().getString(ACTION_MEDIA_ARTIST_KEY);
        //
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
          mMediaPlayer.setDataSource(getApplicationContext(), myUri);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        mMediaPlayer.setWakeMode(getApplicationContext(),
          PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.prepareAsync();
      }
    }

    return START_NOT_STICKY;
  }

  /** Called when MediaPlayer is ready */
  public void onPrepared(MediaPlayer player) {
    isPlaying = true;

    Notification notification =
      new Notification(R.drawable.play_lac, "> " + titleName,
        System.currentTimeMillis());
    Intent i = new Intent(this, MainActivity.class);
    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
    notification.setLatestEventInfo(this, "MediaServiceExample", "Música: "
      + titleName, pi);
    notification.flags |= Notification.FLAG_NO_CLEAR;
    startForeground(100, notification);

    player.start();
  }

  @Override
  public void onDestroy() {
    if (isPlaying && mMediaPlayer != null) {
      mMediaPlayer.stop();
      mMediaPlayer.release();
      mMediaPlayer = null;
      isPlaying = false;
    }
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent arg0) {
    return null;
  }
}