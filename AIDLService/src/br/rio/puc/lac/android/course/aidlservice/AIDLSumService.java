package br.rio.puc.lac.android.course.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLSumService extends Service {

  public class SumServiceImpl extends ISumService.Stub {

    @Override
    public long soma(long a, long b) throws RemoteException {
      return AIDLSumService.this.soma(a, b);
    }

    @Override
    public long somaP(Soma soma) throws RemoteException {
      return AIDLSumService.this.soma(soma.first, soma.second);
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent arg0) {
    return new SumServiceImpl();
  }

  public long soma(long a, long b) {
    return a + b;
  }
}
