package br.rio.puc.lac.android.course.downloadservice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message message) {
      Object path = message.obj;
      if (message.arg1 == RESULT_OK && path != null) {
        Toast.makeText(MainActivity.this,
          "Conteúdo baixado: " + path.toString(), Toast.LENGTH_LONG).show();
      }
      else {
        Toast.makeText(MainActivity.this, "O download falhou.",
          Toast.LENGTH_LONG).show();
      }

    };
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View view) {
    Intent intent = new Intent(this, DownloadService.class);

    String enderecoUrl = "http://www.lac-rio.com/index.php";

    Toast.makeText(MainActivity.this, enderecoUrl, Toast.LENGTH_LONG).show();

    Messenger messenger = new Messenger(handler);
    intent.putExtra("MESSENGER", messenger);
    intent.setData(Uri.parse(enderecoUrl));
    intent.putExtra("urlpath", enderecoUrl);
    startService(intent);
  }
}