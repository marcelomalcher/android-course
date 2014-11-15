package br.rio.puc.lac.android.course.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  static final int ALERT_DIALOG_ID = 0;
  static final int ALERT_DIALOG_LIST_ID = 1;
  static final int ALERT_DIALOG_LIST_MULTI_ID = 2;
  static final int ALERT_DIALOG_LIST_SINGLE_ID = 3;
  static final int PROGRESS_DIALOG_WHEEL = 4;
  static final int PROGRESS_DIALOG_BAR = 5;

  private ProgressDialog progressDialog;
  private ProgressThread progressThread;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    Dialog dialog;
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    final CharSequence[] items = { "Vermelho", "Verde", "Azul" };
    switch (id) {
      case ALERT_DIALOG_ID:
        builder.setMessage("Este é um exemplo de dialog, ok?")
          .setCancelable(false)
          .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              MainActivity.this.finish();
            }
          }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              dialog.cancel();
            }
          });
        dialog = builder.create();
        break;
      case ALERT_DIALOG_LIST_ID:
        builder.setTitle("Escolha a cor");
        builder.setItems(items, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int item) {
            Toast.makeText(getApplicationContext(), items[item],
              Toast.LENGTH_SHORT).show();
          }
        });
        dialog = builder.create();
        break;
      case ALERT_DIALOG_LIST_MULTI_ID:
        builder.setTitle("Escolha a cor");
        builder.setMultiChoiceItems(items,
          new boolean[] { false, false, false },
          new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item,
              boolean isChecked) {
              Toast.makeText(getApplicationContext(),
                items[item] + " = " + isChecked, Toast.LENGTH_SHORT).show();
            }
          });
        dialog = builder.create();
        break;
      case ALERT_DIALOG_LIST_SINGLE_ID:
        builder.setTitle("Escolha a cor");
        builder.setSingleChoiceItems(items, -1,
          new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
              Toast.makeText(getApplicationContext(), items[item],
                Toast.LENGTH_SHORT).show();
            }
          });
        dialog = builder.create();
        break;
      case PROGRESS_DIALOG_WHEEL:
        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setTitle("");
        pDialog.setMessage("Carregando. Por favor, espere...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        dialog = pDialog;
        break;
      case PROGRESS_DIALOG_BAR:
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Carregando...");
        progressDialog.setCancelable(false);
        dialog = progressDialog;
        break;
      default:
        dialog = null;
    }
    return dialog;
  }

  @Override
  protected void onPrepareDialog(int id, Dialog dialog) {
    switch (id) {
      case PROGRESS_DIALOG_BAR:
        progressDialog.setProgress(0);
        progressThread = new ProgressThread(handler);
        progressThread.start();
    }
  }

  public void onClick(View v) {
    if (v.getId() == R.id.button1) {
      showDialog(ALERT_DIALOG_ID);
    }
    else if (v.getId() == R.id.button2) {
      showDialog(ALERT_DIALOG_LIST_ID);
    }
    else if (v.getId() == R.id.button3) {
      showDialog(ALERT_DIALOG_LIST_MULTI_ID);
    }
    else if (v.getId() == R.id.button4) {
      showDialog(ALERT_DIALOG_LIST_SINGLE_ID);
    }
    else if (v.getId() == R.id.button5) {
      showDialog(PROGRESS_DIALOG_WHEEL);
    }
    else if (v.getId() == R.id.button6) {
      showDialog(PROGRESS_DIALOG_BAR);
    }
  }

  //Define the Handler that receives messages from the thread and update the progress
  final Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      int total = msg.arg1;
      progressDialog.setProgress(total);
      if (total >= 100) {
        dismissDialog(PROGRESS_DIALOG_BAR);
        progressThread.setState(ProgressThread.STATE_DONE);
      }
    }
  };

  private class ProgressThread extends Thread {
    Handler mHandler;
    final static int STATE_DONE = 0;
    final static int STATE_RUNNING = 1;
    int mState;
    int total;

    ProgressThread(Handler h) {
      mHandler = h;
    }

    @Override
    public void run() {
      mState = STATE_RUNNING;
      total = 0;
      while (mState == STATE_RUNNING) {
        try {
          Thread.sleep(100);
        }
        catch (InterruptedException e) {
        }
        Message msg = mHandler.obtainMessage();
        msg.arg1 = total;
        mHandler.sendMessage(msg);
        total++;
      }
    }

    public void setState(int state) {
      mState = state;
    }
  }
}