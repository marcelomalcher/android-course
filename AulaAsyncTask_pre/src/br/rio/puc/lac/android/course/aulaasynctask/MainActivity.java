package br.rio.puc.lac.android.course.aulaasynctask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

  Button btnStart, btnSend;

  EditText edtText;

  TextView textStatus;

  NetworkTask networktask;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btnStart = (Button) findViewById(R.id.btnStart);
    btnSend = (Button) findViewById(R.id.btnSend);

    edtText = (EditText) findViewById(R.id.editText1);

    textStatus = (TextView) findViewById(R.id.textStatus);

    btnStart.setOnClickListener(btnStartListener);

    btnSend.setOnClickListener(btnSendListener);

    controlViews(true);
  }

  private void controlViews(boolean flag) {
    btnStart.setEnabled(flag);
    edtText.setEnabled(!flag);
    btnSend.setEnabled(!flag);
  }

  private OnClickListener btnStartListener = new OnClickListener() {
    public void onClick(View v) {

      //
      //TAREFA
      //- Crie uma instância de AsyncTask
      //- Execute este instância
      //

      controlViews(false);
    }
  };
  private OnClickListener btnSendListener = new OnClickListener() {
    public void onClick(View v) {
      textStatus.setText("Sending Message to AsyncTask.");
      networktask.SendDataToNetwork(edtText.getText().toString());
    }
  };

  public class NetworkTask extends AsyncTask<Void, byte[], Boolean> {

    Socket nsocket;
    DataInputStream nis;
    DataOutputStream nos;

    @Override
    protected void onPreExecute() {
      Log.i("AsyncTask", "onPreExecute");
    }

    @Override
    protected Boolean doInBackground(Void... params) { //This runs on a different thread
      boolean result = false;
      try {
        Log.i("AsyncTask", "doInBackground: Creating socket");

        SocketAddress sockaddr =
          new InetSocketAddress("ec2-23-22-247-124.compute-1.amazonaws.com",
            8888);

        nsocket = new Socket();
        nsocket.connect(sockaddr, 10000);

        if (nsocket.isConnected()) {

          //
          //TAREFA
          //- Crie uma nova instância de DataInputStream - stream de entrada
          //- Crie uma nova instância de DataOuputStream - stream de saída
          //

          Log
            .i("AsyncTask", "doInBackground: Socket created, streams assigned");
          Log.i("AsyncTask", "doInBackground: Waiting for inital data...");

          byte[] buffer = new byte[4096];
          int read = nis.read(buffer, 0, 4096); //This is blocking
          while (read != -1) {

            byte[] tempdata = new byte[read];

            System.arraycopy(buffer, 0, tempdata, 0, read);

            publishProgress(tempdata);
            //
            //TAREFA
            //- Atualize o usuário acerca do progresso do stream de leitura através do método que irá chamar onProgressUpdate()
            //

            read = nis.read(buffer, 0, 4096); //This is blocking

          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
        result = true;
      }
      catch (Exception e) {
        e.printStackTrace();
        result = true;
      }
      finally {
        try {
          nis.close();
          nos.close();
          nsocket.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
        Log.i("AsyncTask", "doInBackground: Finished");
      }
      return result;
    }

    public void SendDataToNetwork(String cmd) { //You run this from the main thread.
      try {
        if (nsocket.isConnected()) {
          nos.writeUTF(cmd);
          nos.flush();
        }
        else {
        }
      }
      catch (Exception e) {
      }
    }

    @Override
    protected void onProgressUpdate(byte[]... values) {
      if (values.length > 0) {
        //
        //TAREFA 
        //- Atualize o status (TextView) com o texto já obtido
        //        
      }
    }

    @Override
    protected void onCancelled() {
      controlViews(true);
    }

    @Override
    protected void onPostExecute(Boolean result) {
      if (result) {
        Log.i("AsyncTask", "onPostExecute: Completed with an Error.");
        textStatus.setText("There was a connection error.");
      }
      else {
        Log.i("AsyncTask", "onPostExecute: Completed.");
      }
      controlViews(true);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    networktask.cancel(true);
  }
}