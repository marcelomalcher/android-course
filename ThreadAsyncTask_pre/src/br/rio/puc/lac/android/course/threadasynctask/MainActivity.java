package br.rio.puc.lac.android.course.threadasynctask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
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

  //TAREFA - Fazer com que DownloadWebPageTask estenda AsyncTask e utilize os tipos generics String, Void, String 
  private class DownloadWebPageTask {

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
      //TAREFA - retornar o conteúdo do site recebido por stream como um texto
    }

    //TAREFA - Implementar método onPostExecute para que a TextView receba o resultado
  }

  public void readWebpage(View view) {
    //TAREFA - instanciar a AsyncTask DownloadWebPageTask e executá-la com o parâmetro de endereço http://www.lac-rio.com
  }
}
