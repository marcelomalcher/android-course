package br.rio.puc.lac.android.course.connectivitymanager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ConnectivityManagerActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ConnectivityManager cm =
      (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
    TextView tv = (TextView) findViewById(R.id.tvANName);
    tv.setText(networkInfo.getTypeName());
    ToggleButton tb = (ToggleButton) findViewById(R.id.tbActiveNetwork);
    tb.setChecked(networkInfo != null && networkInfo.isConnectedOrConnecting());

    networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    tb = (ToggleButton) findViewById(R.id.tbWifi);
    tb.setChecked(networkInfo != null && networkInfo.isConnectedOrConnecting());

    networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    tb = (ToggleButton) findViewById(R.id.tbMobile);
    tb.setChecked(networkInfo != null && networkInfo.isConnectedOrConnecting());
  }
}