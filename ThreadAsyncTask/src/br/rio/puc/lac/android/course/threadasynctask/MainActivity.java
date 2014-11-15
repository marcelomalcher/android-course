package br.rio.puc.lac.android.course.threadasynctask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  private TextView textView;

  /** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    textView = (TextView) findViewById(R.id.TextView01);
  }

  private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
      String response = "";
      for (String theUrl : urls) {
        try {
          URL url = new URL(theUrl);
          HttpURLConnection urlConnection =
            (HttpURLConnection) url.openConnection();

          InputStream content = urlConnection.getInputStream();

          BufferedReader buffer =
            new BufferedReader(new InputStreamReader(content));
          String s = "";
          while ((s = buffer.readLine()) != null) {
            response += s;
          }

        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      return response;
    }

    @Override
    protected void onPostExecute(String result) {
      textView.setText(result);
    }
  }

  public void readWebpage(View view) {
    DownloadWebPageTask task = new DownloadWebPageTask();
    task.execute(new String[] { "http://www.lac-rio.com" });

  }
}
