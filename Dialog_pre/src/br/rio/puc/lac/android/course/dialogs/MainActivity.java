package br.rio.puc.lac.android.course.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

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
        //TAREFA
        //- Utilize o método setMessage() para definir a mensagem a ser criada
        //- Defina que o diálogo não pode ser cancelado com setCancelable(false)
        //- Defina um botão positivo com texto 'Sim' e no clique botão finalize a Activity - MainActivity.this.finish()
        //- Defina um botão negativo com texto 'Não' e que apenas cancele o diálogo - dialog.cancel()
        //- Faça com que o objeto dialog receba a referência de builder.create()
        break;
      case ALERT_DIALOG_LIST_ID:
        //TAREFA
        //- Defina o título do diálogo como 'Escolha a cor'
        //- Defina os itens do diálogo com o array já definido com o método setItems() 
        //--- No clique de um ítem mostre a cor escolhida via Toast
        //- Faça com que o objeto dialog receba a referência de builder.create()         
        break;
      case ALERT_DIALOG_LIST_MULTI_ID:
        //TAREFA
        //- Defina o título do diálogo como 'Escolha a cor'
        //- Defina os itens do diálogo com o array já definido com o método setMultiChoiceItems()
        //--- Na hora de checar cada item, mostre no Toast o estado de cada opção
        //- Faça com que o objeto dialog receba a referência de builder.create()         
        break;
      case ALERT_DIALOG_LIST_SINGLE_ID:
        //TAREFA
        //- Defina o título do diálogo como 'Escolha a cor'
        //- Defina os itens do diálogo com o array já definido com o método setSingleChoiceItems()
        //--- No clique de um ítem mostre a cor escolhida via Toast
        //- Faça com que o objeto dialog receba a referência de builder.create()           
        break;
      case PROGRESS_DIALOG_WHEEL:
        //TAREFA
        //- Crie um novo ProgressDialog com new ProgressDialog
        //- Não conterá título - setTitle("")
        //- Defina a mensagem para 'Carregando. Por favor, espere'
        //- Defina como indeterminado - setIndeterminate(true)
        //- Defina como cancelável - setCancelable(true)
        //- Faça com que o objeto dialog receba a referência ao ProgressDialog criado
        break;
      case PROGRESS_DIALOG_BAR:
        //TAREFA
        //- Crie um novo ProgressDialog com new ProgressDialog
        //- Defina o estildo do progresso com setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        //- Defina a mensagem para 'Carregando...'
        //- Defina como não cancelável - setCancelable(false)
        //- Faça com que o objeto dialog receba a referência ao ProgressDialog criado
        //- Veja o método onPrepareDialog() que inicia uma thread para controlar a barra de progresso
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