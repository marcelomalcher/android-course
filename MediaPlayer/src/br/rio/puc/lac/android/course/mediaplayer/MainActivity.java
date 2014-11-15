package br.rio.puc.lac.android.course.mediaplayer;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  private MediaPlayer mediaPlayer;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  /**
   * 
   * @param v
   */
  public void onClick(View v) {
    try {
      String url;
      switch (v.getId()) {
        case R.id.bResLocal:
          mediaPlayer =
            MediaPlayer.create(getApplicationContext(), R.raw.music);
          mediaPlayer.start();
          break;

        case R.id.bURIin:

          final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          final String[] cursor_cols =
            { MediaStore.Audio.Media.DATA, MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE };
          final String where = MediaStore.Audio.Media.IS_MUSIC + "=1";
          final Cursor cursor =
            getContentResolver().query(uri, cursor_cols, where, null, null);
          //
          cursor.moveToNext();
          String data =
            cursor.getString(cursor
              .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
          Toast.makeText(getApplicationContext(), "Data: " + data,
            Toast.LENGTH_LONG).show();

          Uri myUri = Uri.parse(data); // inicializar URI interna aqui (provavelmente obtida a partir de ContentProvider)
          mediaPlayer = new MediaPlayer();
          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
          mediaPlayer.setDataSource(getApplicationContext(), myUri);
          mediaPlayer.prepare();
          mediaPlayer.start();

          break;

        case R.id.bURIext:

          url =
            "http://www.inf.puc-rio.br/~marcelom/androidcourse/files/music.mp3"; // A URL do arquivo da internet
          mediaPlayer = new MediaPlayer();
          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
          mediaPlayer.setDataSource(url);
          mediaPlayer.prepare(); // pode demorar (para buferização, etc)
          mediaPlayer.start();

          break;

        case R.id.bURIextAsync:

          url =
            "http://www.inf.puc-rio.br/~marcelom/androidcourse/files/music.mp3"; // A URL do arquivo da internet
          mediaPlayer = new MediaPlayer();
          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
          mediaPlayer.setDataSource(url);
          mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
              mediaPlayer.start(); //irá executar quando a mídia estiver preparada            
            }
          });
          mediaPlayer.setOnErrorListener(new OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
              return false;
            }
          });
          mediaPlayer.prepareAsync(); // não bloqueia a thread        

          break;
      }
    }
    catch (IOException e) {

    }
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  protected void onStop() {
    if (mediaPlayer != null) {
      mediaPlayer.release();
      mediaPlayer = null;
    }
    super.onStop();
  }
}