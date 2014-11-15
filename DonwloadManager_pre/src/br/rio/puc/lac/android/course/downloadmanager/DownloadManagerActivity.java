package br.rio.puc.lac.android.course.downloadmanager;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DownloadManagerActivity extends Activity {
  private long enqueue;
  private DownloadManager dm;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    BroadcastReceiver receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
          long downloadId =
            intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
          if (downloadId != enqueue) {
            return;
          }
          Query query = new Query();
          query.setFilterById(enqueue);
          Cursor c = dm.query(query);
          if (c.moveToFirst()) {
            int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
            if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {

              ImageView view = (ImageView) findViewById(R.id.imageView1);
              String uriString =
                c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
              view.setImageURI(Uri.parse(uriString));
            }
          }
        }
      }
    };

    //TAREFA - Registar o BroadcastReceiver para downloads do DownloadManager requisitando como action DownloadManager.ACTION_DOWNLOAD_COMPLETE    
  }

  public void onClick(View view) {
    //TAREFA 
    //- Receber instância do DownloadManager utiliando método getSystemService
    //- Criar novo objeto Request passando a URI do arquivo (deve ser o link de uma imagem) a ser baixado
    //- Enfileirar o download deste arquivo na fila de downloads do DownloadMananager
  }

  public void showDownload(View view) {
    Intent i = new Intent();
    i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
    startActivity(i);
  }
}