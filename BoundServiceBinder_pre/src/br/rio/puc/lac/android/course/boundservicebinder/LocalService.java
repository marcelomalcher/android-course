package br.rio.puc.lac.android.course.boundservicebinder;

import java.util.Random;

import android.os.Binder;

public class LocalService {

  //TAREFA - Criar atributo do tipo IBinder e instanci�-lo como LocalBinder

  public class LocalBinder extends Binder {
    LocalService getService() {
      // Retorna inst�ncia do service
      return LocalService.this;
    }
  }

  // Gerador rand�mico de n�meros
  private final Random mGenerator = new Random();

  /** M�todo p�blico */
  public int getRandomNumber() {
    return mGenerator.nextInt(100);
  }

  //TAREFA - Implmentar o m�todo onBind() para retornar objeto do tipo IBinder
}