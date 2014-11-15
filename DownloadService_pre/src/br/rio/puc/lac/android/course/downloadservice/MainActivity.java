package br.rio.puc.lac.android.course.downloadservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  private Handler handler;

  //Criar objeto Handler para tratar a mensagem que o service irá enviar
  //Verificar se o retorno do service é positivo ou negativo
  //Mostrar uma mensagem utilizando Toast para o usuário

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View view) {
    String enderecoUrl = "http://www.lac-rio.com/index.php";
    Toast.makeText(MainActivity.this, enderecoUrl, Toast.LENGTH_LONG).show();

    //Criar intent para DownloadService
    //Criar Messenger passando como parâmetro o handler definido
    //Adicionar ao intent como extras: o Messenger criado e o endereco identificado como "urlpath"
    //Adicionar ao intent como data: o endereço como um Uri
    //Iniciar o service    
  }
}