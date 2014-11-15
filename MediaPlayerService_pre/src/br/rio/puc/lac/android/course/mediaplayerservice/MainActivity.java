package br.rio.puc.lac.android.course.mediaplayerservice;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends Activity {

  private Intent intent;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View v) {
    if (v.getId() == R.id.button1) {
      //
      final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
      final String[] cursor_cols =
        { MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE };
      final String where = MediaStore.Audio.Media.IS_MUSIC + "=1";
      final Cursor cursor =
        getContentResolver().query(uri, cursor_cols, where, null, null);
      cursor.moveToNext();
      String data =
        cursor.getString(cursor
          .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
      String title =
        cursor.getString(cursor
          .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
      String artist =
        cursor.getString(cursor
          .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

      //TAREFA 
      //- Criar novo intent com action igual a MediaService.ACTION_PLAY
      //- Colocar como extra do intent os seguintes pares chave-valor
      //----- MediaService.ACTION_MEDIA_URL_KEY : data
      //----- MediaService.ACTION_MEDIA_TITLE_KEY : title
      //----- MediaService.ACTION_MEDIA_ARTIST_KEY : artist
      //- Chamar método para iniciar service com intent criado
    }
    else if (v.getId() == R.id.button2) {
      if (intent == null) {
        intent = new Intent(MediaService.ACTION_PLAY);
      }
      stopService(intent);
    }
  }
}