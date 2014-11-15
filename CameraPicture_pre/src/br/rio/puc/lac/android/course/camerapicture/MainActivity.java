package br.rio.puc.lac.android.course.camerapicture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

  private ImageView im;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    im = (ImageView) findViewById(R.id.imageView1);
  }

  public void onClick(View v) {
    //TAREFA 
    //-Criar intent com action igual a MediaStore.ACTION_IMAGE_CAPTURE
    //-Iniciar activit para o intent criado com o código de resultado definido na classe
    //----Logo, utilizar startActivityForResult()
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Toast.makeText(this, "Imagem gravada em:\n" + data.getData(),
          Toast.LENGTH_LONG).show();
        //TAREFA 
        //-Receber a foto tirada (bitmap) a partir da chave 'data' dos extras do intent
        //-Mostrar esta imagem no objeto ImageView
      }
      else if (resultCode == RESULT_CANCELED) {
      }
      else {
      }
    }
  }
}