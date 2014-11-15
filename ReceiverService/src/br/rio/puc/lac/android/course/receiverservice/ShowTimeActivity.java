package br.rio.puc.lac.android.course.receiverservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowTimeActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    TextView tv = (TextView) findViewById(R.id.tv);

    if (getIntent() != null && getIntent().getExtras() != null) {
      String time = getIntent().getExtras().getString(ReceiverService.TIME_KEY);
      tv.setText(time);
    }
  }

  public void onClick(View v) {
    stopService(new Intent(this, ReceiverService.class));
    finish();
  }
}