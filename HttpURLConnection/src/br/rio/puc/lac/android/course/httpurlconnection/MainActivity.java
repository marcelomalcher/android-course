package br.rio.puc.lac.android.course.httpurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

  private EditText etUrl;
  private EditText etContent;

  private Button b;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    etUrl = (EditText) findViewById(R.id.etURL);
    etContent = (EditText) findViewById(R.id.etContent);
    b = (Button) findViewById(R.id.button1);
  }

  public void onClick(View v) {
    if (v == b) {
      try {
        URL url = new URL(etUrl.getText().toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        readStream(con.getInputStream());
      }
      catch (Exception e) {
      }
    }
  }

  private void readStream(InputStream in) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(in));
      String line = "";
      while ((line = reader.readLine()) != null) {
        etContent.append(line);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      if (reader != null) {
        try {
          reader.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}