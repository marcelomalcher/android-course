package br.rio.puc.lac.android.course.appwidgetconfig;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Configure extends Activity {

  private Configure context;

  private int widgetId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.configure);
    setResult(RESULT_CANCELED);

    context = this;

    //TAREFA
    //- Receba o identificador do widget a partir dos extras do intent
    //--- A chave é AppWidgetManager.EXTRA_APPWIDGET_ID e utilize como valor de default AppWidgetManager.INVALID_APPWIDGET_ID
    //- Receba a instância (singleton) do AppWidgetManager
    //- Crie um objeto RemoteViews utilizando o PackageName do contexto atual e o arquivo de layout do widget (R.layout.main)    

    final EditText et = (EditText) findViewById(R.id.editText1);
    Button b = (Button) findViewById(R.id.button1);
    b.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        //TAREFA
        //- Crie um intento com action igual a Intent.ACTION_VIEW e uri igual ao endereço atribuído pelo usuário
        //- Crie um PendingIntent a partir do método estático getActivity passando o contexto atual e o intent criado
        //- Com o objeto RemoteViews defina o clique do botão do widget com setOnClickPendingIntent passando o id do botão do widget e o PendingIntent criado
        //- A partir da instância do AppWidgetManager, atualize o widget com updateAppWidget
        //- Crie um novo intent que será enviado ao widget criado informando que foi criado com sucesso
        //--- Adicione o extra com chave igual a AppWidgetManager.EXTRA_APPWIDGET_ID e valor igual ao id do widget recuperado inicialmente
        //- Defina o resultado da Activity como RESULT_OK e passe o intent criado
        //- Finalize a Activity
      }
    });

  }
}
