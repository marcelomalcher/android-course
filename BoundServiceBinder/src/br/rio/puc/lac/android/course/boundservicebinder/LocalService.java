package br.rio.puc.lac.android.course.boundservicebinder;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {
  // Binder dado aos clientes
  private final IBinder mBinder = new LocalBinder();
  // Gerador rand�mico de n�meros
  private final Random mGenerator = new Random();

  public class LocalBinder extends Binder {
    LocalService getService() {
      // Retorna inst�ncia do service
      return LocalService.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  /** M�todo p�blico */
  public int getRandomNumber() {
    return mGenerator.nextInt(100);
  }
}