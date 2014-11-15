package br.rio.puc.lac.android.course.camerapicture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Toast.makeText(this, "Imagem gravada em:\n" + data.getData(),
          Toast.LENGTH_LONG).show();
        Bitmap bm = (Bitmap) data.getExtras().get("data");
        im.setImageBitmap(bm);
      }
      else if (resultCode == RESULT_CANCELED) {
      }
      else {
      }
    }
  }
}