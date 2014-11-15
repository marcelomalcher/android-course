package br.rio.puc.lac.android.course.downloadservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class DownloadService extends IntentService {

  private int result = Activity.RESULT_CANCELED;

  public DownloadService() {
    super("DownloadService");
  }

  @Override
  protected void onHandleIntent(Intent intent) {
    Uri data = intent.getData();
    String urlPath = intent.getStringExtra("urlpath");
    String fileName = data.getLastPathSegment();
    File output = new File(Environment.getExternalStorageDirectory(), fileName);
    if (output.exists()) {
      output.delete();
    }

    InputStream stream = null;
    FileOutputStream fos = null;
    try {

      URL url = new URL(urlPath);
      stream = url.openConnection().getInputStream();
      InputStreamReader reader = new InputStreamReader(stream);
      fos = new FileOutputStream(output.getPath());
      int next = -1;
      while ((next = reader.read()) != -1) {
        fos.write(next);
      }
      result = Activity.RESULT_OK;

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (stream != null) {
        try {
          stream.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fos != null) {
        try {
          fos.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    Bundle extras = intent.getExtras();
    if (extras != null) {
      //Obter o messenger a partir dos extras
      //Criar a mensagem
      //Atribuir o resultado para o arg1
      //Atribuir o caminho gravado em obj
      try {
        //Enviar a mensagem
      }
      catch (android.os.RemoteException e1) {
        Log.w(getClass().getName(), "Exception sending message", e1);
      }

    }
  }
}