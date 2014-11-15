package br.rio.puc.lac.android.course.mediaplayer;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

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

          //TAREFA
          //- Instanciar o MediaPlayer a partir do método estático create() utilizando o arquivo de recurso raw/music e iniciar o mesmo

          break;

        case R.id.bURIin:

          //ATENÇÃO!
          //Este código acessa o ContentProvider para mídias no Android, 
          //tentando recuperar apenas as músicas no dispositivo
          //Com a primeira música encontrada, recupera a URI da mesma          
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

          //TAREFA 
          //- Criar objeto Uri a partir do parsing do objeto String data
          //- Instanciar novo MediaPlayer, definindo como tipo de stream a constante AudioManager.STREAM_MUSIC
          //- Definindo o que vai ser executado com setDataSource()
          //- Chamar os métodos prepare() e start()

          break;

        case R.id.bURIext:
          // A URL do arquivo da internet
          url =
            "http://www.inf.puc-rio.br/~marcelom/androidcourse/files/music.mp3";

          //TAREFA 
          //- Instanciar novo MediaPlayer como o anterior, porém definindo o que vai ser executado passando a url
          //- Preparar e executar 

          break;

        case R.id.bURIextAsync:

          url =
            "http://www.inf.puc-rio.br/~marcelom/androidcourse/files/music.mp3"; // A URL do arquivo da internet

          //TAREFA 
          //- Instanciar novo MediaPlayer como o anterior, porém definir listener OnPrepareListener para iniciar a execução do arquivo quando chamado
          //- Utilizar prepareAsync() ao invés de prepare()

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
    //TAREFA 
    //- Verificar se o objeto MediaPlayer é diferente de nulo
    //- Caso seja, existe uma instância criada. Logo, deve-se chamar o método release() e atribuir nulo à mesma       
    super.onStop();
  }
}