package br.rio.puc.lac.android.course.videoview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    VideoView vv = (VideoView) findViewById(R.id.videoView1);
    vv.setVideoPath("/sdcard/vid.mp4");
    vv.setMediaController(new MediaController(this));
    vv.start();
    vv.requestFocus();
  }
}