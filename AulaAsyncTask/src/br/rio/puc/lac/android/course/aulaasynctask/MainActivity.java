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

    networktask = new NetworkTask();
  }

  private void controlViews(boolean flag) {
    btnStart.setEnabled(flag);
    edtText.setEnabled(!flag);
    btnSend.setEnabled(!flag);
  }

  private OnClickListener btnStartListener = new OnClickListener() {
    public void onClick(View v) {
      //
      networktask = new NetworkTask(); //New instance of NetworkTask
      networktask.execute();
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
          nis = new DataInputStream(nsocket.getInputStream());
          nos = new DataOutputStream(nsocket.getOutputStream());

          Log
            .i("AsyncTask", "doInBackground: Socket created, streams assigned");
          Log.i("AsyncTask", "doInBackground: Waiting for inital data...");

          byte[] buffer = new byte[4096];
          int read = nis.read(buffer, 0, 4096); //This is blocking
          while (read != -1) {
            byte[] tempdata = new byte[read];
            System.arraycopy(buffer, 0, tempdata, 0, read);
            publishProgress(tempdata);

            Log.i("AsyncTask", "doInBackground: Got some data");

            read = nis.read(buffer, 0, 4096); //This is blocking
          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
        Log.i("AsyncTask", "doInBackground: IOException");
        result = true;
      }
      catch (Exception e) {
        e.printStackTrace();
        Log.i("AsyncTask", "doInBackground: Exception");
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
          Log.i("AsyncTask",
            "SendDataToNetwork: Writing received message to socket");
          nos.writeUTF(cmd);
          nos.flush();
        }
        else {
          Log.i("AsyncTask",
            "SendDataToNetwork: Cannot send message. Socket is closed");
        }
      }
      catch (Exception e) {
        Log.i("AsyncTask",
          "SendDataToNetwork: Message send failed. Caught an exception");
      }
    }

    @Override
    protected void onProgressUpdate(byte[]... values) {
      if (values.length > 0) {
        Log.i("AsyncTask", "onProgressUpdate: " + values[0].length
          + " bytes received.");
        textStatus.setText(new String(values[0]));
      }
    }

    @Override
    protected void onCancelled() {
      Log.i("AsyncTask", "Cancelled.");
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
    networktask.cancel(true); //In case the task is currently running
  }
}