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
      //- Crie um objeto Toast com m�todo est�tico makeText() com qualquer texto e de longa dura��o 
      //- Utilize o m�todo show() para mostrar o Toast
    }
    else if (v.getId() == R.id.button2) {
      //TAREFA
      //- Crie um objeto Toast com m�todo est�tico makeText() com qualquer texto e de longa dura��o
      //- Defina a posi��o do Toast utilizando o m�todo setGravity() para o centro da tela
      //- Utilize o m�todo show() para mostrar o Toast
    }
    else if (v.getId() == R.id.button3) {
      //TAREFA
      //- Crie um Toast com layout customizado utilizando o objeto LayoutInflater (recupere-o via getLayoutInflater()
      //- Com objeto LayoutInflater, use o m�todo inflate para instanciar a View que ser� usada para o Toast
      //--- No m�todo inflate(), passe como par�metros o arquivo de layout definido para o Toast e o ViewGroup (raiz) obtido com findViewById()
      //- Usando o m�todo findViewById() obtenha a refer�ncia aos objetos ImageView e TextView do layout do Toast
      //- Defina a imagem e o texto a serem mostrados nos objetos ImageView e TextView
      //- Crie um novo objeto Toast com new Toast()
      //- Defina a posi��o como central e a dura��o como longa 
      //- Defina o layout do Toast com o m�todo setView() passando a view instancia pelo m�todo inflate()
      //- Utilize o m�todo show() para mostrar o Toast
    }
  }
}