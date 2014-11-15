package br.rio.puc.lac.android.course.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View v) {
    if (v.getId() == R.id.button1) {
      //TAREFA
      //- Crie um objeto Toast com método estático makeText() com qualquer texto e de longa duração 
      //- Utilize o método show() para mostrar o Toast
    }
    else if (v.getId() == R.id.button2) {
      //TAREFA
      //- Crie um objeto Toast com método estático makeText() com qualquer texto e de longa duração
      //- Defina a posição do Toast utilizando o método setGravity() para o centro da tela
      //- Utilize o método show() para mostrar o Toast
    }
    else if (v.getId() == R.id.button3) {
      //TAREFA
      //- Crie um Toast com layout customizado utilizando o objeto LayoutInflater (recupere-o via getLayoutInflater()
      //- Com objeto LayoutInflater, use o método inflate para instanciar a View que será usada para o Toast
      //--- No método inflate(), passe como parâmetros o arquivo de layout definido para o Toast e o ViewGroup (raiz) obtido com findViewById()
      //- Usando o método findViewById() obtenha a referência aos objetos ImageView e TextView do layout do Toast
      //- Defina a imagem e o texto a serem mostrados nos objetos ImageView e TextView
      //- Crie um novo objeto Toast com new Toast()
      //- Defina a posição como central e a duração como longa 
      //- Defina o layout do Toast com o método setView() passando a view instancia pelo método inflate()
      //- Utilize o método show() para mostrar o Toast
    }
  }
}