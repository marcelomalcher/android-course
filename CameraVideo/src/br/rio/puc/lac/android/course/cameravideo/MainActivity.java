package br.rio.puc.lac.android.course.cameravideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
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
    Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
    startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Toast.makeText(this, "Video saved to:\n" + data.getData(),
          Toast.LENGTH_LONG).show();
        vv.setVideoPath(data.getDataString());
        vv.setMediaController(new MediaController(this));
        vv.start();
        vv.requestFocus();
      }
      else if (resultCode == RESULT_CANCELED) {
      }
      else {
      }
    }
  }
}