package br.rio.puc.lac.android.course.boundservicebinder;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LocalService extends Service {
  // Binder dado aos clientes
  private final IBinder mBinder = new LocalBinder();
  // Gerador randômico de números
  private final Random mGenerator = new Random();

  public class LocalBinder extends Binder {
    LocalService getService() {
      // Retorna instância do service
      return LocalService.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return mBinder;
  }

  /** Método público */
  public int getRandomNumber() {
    return mGenerator.nextInt(100);
  }
}