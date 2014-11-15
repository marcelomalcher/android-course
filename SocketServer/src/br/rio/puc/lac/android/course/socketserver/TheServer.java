package br.rio.puc.lac.android.course.socketserver;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * 
 * 
 * @author Marcelo Malcher
 */
public class TheServer {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println("*** Utilização: TheServer <Porta> <File>");
      System.exit(-1);
    }

    int port = Integer.valueOf(args[0]);

    String file = args[1];

    new TheServer().initialize(port, file);
  }

  /**
   * 
   * @param port
   */
  private void initialize(int port, String file) {
    ServerSocket serverSocket = null;
    Socket socket = null;

    try {

      serverSocket = new ServerSocket(port);
      System.out.println("Servidor executando na porta: " + port);

    }
    catch (IOException e) {
      e.printStackTrace();
    }

    while (true) {
      try {
        socket = serverSocket.accept();

        new Thread(new ConnectionHandler(socket, file)).start();

      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  class ConnectionHandler implements Runnable {

    Socket socket;

    String file;

    DataInputStream dataInputStream = null;

    DataOutputStream dataOutputStream = null;

    ConnectionHandler(Socket socket, String file) {
      this.socket = socket;
      this.file = file;
    }

    @Override
    public void run() {
      try {
        FileWriter fstream = new FileWriter(file, true);
        BufferedWriter out = new BufferedWriter(fstream);

        InetAddress IPConn = socket.getInetAddress();
        String output =
          SimpleDateFormat.getInstance().format(
            Calendar.getInstance().getTime())
            + " Connection from IP: " + IPConn;
        System.out.println(output);
        out.write(output + "\n");

        //Input
        dataInputStream = new DataInputStream(socket.getInputStream());

        //Output
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String message = dataInputStream.readUTF();

        output =
          SimpleDateFormat.getInstance().format(
            Calendar.getInstance().getTime())
            + " IP: " + IPConn + " - Message arrived: " + message;
        System.out.println(output);
        out.write(output + "\n");

        out.close();

        dataOutputStream.writeUTF("Olá! Sua mensagem foi: " + message);
      }

      catch (IOException e) {
        e.printStackTrace();
      }
      finally {
        if (socket != null) {
          try {
            socket.close();
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }

        if (dataInputStream != null) {
          try {
            dataInputStream.close();
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }

        if (dataOutputStream != null) {
          try {
            dataOutputStream.close();
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}