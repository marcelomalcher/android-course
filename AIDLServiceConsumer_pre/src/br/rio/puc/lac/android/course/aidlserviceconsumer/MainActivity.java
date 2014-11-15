package br.rio.puc.lac.android.course.aidlserviceconsumer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.rio.puc.lac.android.course.aidlservice.ISumService;
import br.rio.puc.lac.android.course.aidlservice.Soma;

public class MainActivity extends Activity implements OnClickListener {
  private boolean mBound = false;

  private EditText mET1;
  private EditText mET2;

  private Button mBindServiceBtn;
  private Button mUnbindServiceBtn;

  private Button mSumBtn;

  private Button mSumParcelableBtn;

  private ISumService mSumService;

  private ServiceConnection mServiceConn = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      //TAREFA - Utilizar método asInterface da classe ISumService.Stub para associar Binder
      Toast.makeText(getApplicationContext(), "service connected",
        Toast.LENGTH_LONG);
      mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      mBound = false;
      mSumService = null;
      Toast.makeText(getApplicationContext(), "service disconnected",
        Toast.LENGTH_LONG);
    }
  };

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mBindServiceBtn = (Button) findViewById(R.id.btnBindService);
    mBindServiceBtn.setOnClickListener(this);

    mUnbindServiceBtn = (Button) findViewById(R.id.btnUnbindService);
    mUnbindServiceBtn.setOnClickListener(this);
    mUnbindServiceBtn.setEnabled(false);

    mSumBtn = (Button) findViewById(R.id.btnSoma);
    mSumBtn.setOnClickListener(this);
    mSumBtn.setEnabled(false);

    mSumParcelableBtn = (Button) findViewById(R.id.btnSomaParcelable);
    mSumParcelableBtn.setOnClickListener(this);
    mSumParcelableBtn.setEnabled(false);

    mET1 = (EditText) findViewById(R.id.edtxtA);
    mET2 = (EditText) findViewById(R.id.edtxtB);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnBindService:
        //TAREFA - Conectar com Service definindo como action do intent o nome completo da interface declarada no arquivo AIDL
        mBindServiceBtn.setEnabled(false);
        mSumBtn.setEnabled(true);
        mSumParcelableBtn.setEnabled(true);
        mUnbindServiceBtn.setEnabled(true);
        break;
      case R.id.btnSoma:
        if (mBound) {
          remoteSoma(Long.parseLong(mET1.getText().toString()),
            Long.parseLong(mET2.getText().toString()));
        }
        break;
      case R.id.btnSomaParcelable:
        if (mBound) {
          Soma soma = new Soma();
          soma.first = Long.parseLong(mET1.getText().toString());
          soma.second = Long.parseLong(mET2.getText().toString());
          remoteSomaParcelable(soma);
        }
        break;
      case R.id.btnUnbindService:
        unbindService(mServiceConn);
        mBindServiceBtn.setEnabled(true);
        mSumBtn.setEnabled(false);
        mSumParcelableBtn.setEnabled(false);
        mUnbindServiceBtn.setEnabled(false);
        break;
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  private void remoteSoma(long a, long b) {
    try {
      long v = mSumService.soma(a, b);
      Toast.makeText(getApplicationContext(), "soma=" + v, Toast.LENGTH_LONG)
        .show();
    }
    catch (RemoteException ex) {
      Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG)
        .show();
    }
  }

  private void remoteSomaParcelable(Soma soma) {
    try {
      long v = mSumService.somaP(soma);
      Toast.makeText(getApplicationContext(), "somaP=" + v, Toast.LENGTH_LONG)
        .show();
    }
    catch (RemoteException ex) {
      Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG)
        .show();
    }
  }
}