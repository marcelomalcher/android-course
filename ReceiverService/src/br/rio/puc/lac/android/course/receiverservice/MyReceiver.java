package br.rio.puc.lac.android.course.receiverservice;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    Calendar c = Calendar.getInstance();
    String time = c.getTime().toLocaleString() + " -> LAC@PUC-Rio";

    Intent service = new Intent(context, ReceiverService.class);
    service.putExtra(ReceiverService.TIME_KEY, time);
    context.startService(service);
  }
}