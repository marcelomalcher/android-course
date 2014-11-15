package br.rio.puc.lac.android.course.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class Main extends AppWidgetProvider {

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
    int[] appWidgetIds) {
    super.onUpdate(context, appWidgetManager, appWidgetIds);

    for (int i = 0; i < appWidgetIds.length; i++) {
      int appWidgetId = appWidgetIds[i];

      Intent intent =
        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lac-rio.com"));

      PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

      RemoteViews views =
        new RemoteViews(context.getPackageName(), R.layout.main);
      views.setOnClickPendingIntent(R.id.imageButton1, pi);

      appWidgetManager.updateAppWidget(appWidgetId, views);
    }
  }
}
