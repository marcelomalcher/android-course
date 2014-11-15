package br.rio.puc.lac.android.course.boundservicebinder;

import java.util.Random;

import android.os.Binder;

public class LocalService {

  //TAREFA - Criar atributo do tipo IBinder e instanciá-lo como LocalBinder

  public class LocalBinder extends Binder {
    LocalService getService() {
      // Retorna instância do service
      return LocalService.this;
    }
  }

  // Gerador randômico de números
  private final Random mGenerator = new Random();

  /** Método público */
  public int getRandomNumber() {
    return mGenerator.nextInt(100);
  }

  //TAREFA - Implmentar o método onBind() para retornar objeto do tipo IBinder
}