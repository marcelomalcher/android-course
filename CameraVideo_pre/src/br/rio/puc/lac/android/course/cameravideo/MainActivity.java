package br.rio.puc.lac.android.course.cameravideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 100;

  private VideoView vv;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    vv = (VideoView) findViewById(R.id.videoView1);
  }

  public void onClick(View v) {
    //TAREFA 
    //-Criar intent com action igual a MediaStore.ACTION_VIDEO_CAPTURE
    //-Iniciar activit para o intent criado com o código de resultado definido na classe
    //----Logo, utilizar startActivityForResult()
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Toast.makeText(this, "Video saved to:\n" + data.getData(),
          Toast.LENGTH_LONG).show();
        //TAREFA 
        //-Receber o caminho para o video tirado a partir do intent recebido
        //-Atribuir este caminho para o VideoView da activity
        //-Iniciar o video
      }
      else if (resultCode == RESULT_CANCELED) {
      }
      else {
      }
    }
  }
}