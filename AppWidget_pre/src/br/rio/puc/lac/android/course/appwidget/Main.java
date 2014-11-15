package br.rio.puc.lac.android.course.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

// TAREFA
// - Estenda a classe AppWidgetProvider
public class Main {

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
    int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);

    for (int i = 0; i < appWidgetIds.length; i++) {

      int appWidgetId = appWidgetIds[i];

      Intent intent =
        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lac-rio.com"));
      PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

      //TAREFA 
      //- Crie um novo objeto RemoteViews passando o nome do package e o arquivo de layout do widget
      //- Defina para o clique do botão o disparo do pendingIntent com setOnClickPendingIntent()
      //- Com o AppWidgetManager atualize o widget a partir de seu id e objeto views
    }
  }
}
