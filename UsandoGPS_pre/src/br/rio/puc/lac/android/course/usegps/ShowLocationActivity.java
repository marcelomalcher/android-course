package br.rio.puc.lac.android.course.usegps;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowLocationActivity extends Activity implements LocationListener {

  private TextView latituteField;
  private TextView longitudeField;
  private LocationManager locationManager;
  private String provider;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.showactivity);

    latituteField = (TextView) findViewById(R.id.TextView02);
    longitudeField = (TextView) findViewById(R.id.TextView04);
    latituteField.setText("---");
    longitudeField.setText("---");

    //TAREFA
    //- Recuperar serviço de localização (LocationManager)
    //- A partir do provider de GPS (LocationManager.GPS_PROVIDER) recuperar última localização identificada
    //- Se localização for válida, mostrar as coordenadas nos campos EditText
  }

  @Override
  protected void onResume() {
    super.onResume();
    //TAREFA - Requisitar atualizações de localização para o provider de GPS e para esta classe como listener
  }

  @Override
  protected void onPause() {
    super.onPause();
    //TAREFA - Fazer com que esta classe pare de receber atualizações de localização
  }

  @Override
  public void onLocationChanged(Location location) {
    //TAREFA - A partir do parâmetro de localização, mostrar as coordenadas atuais nos campos EditText
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override
  public void onProviderEnabled(String provider) {
    Toast
      .makeText(this, "Enabled new provider " + provider, Toast.LENGTH_SHORT)
      .show();
  }

  @Override
  public void onProviderDisabled(String provider) {
    Toast.makeText(this, "Disabled provider " + provider, Toast.LENGTH_SHORT)
      .show();
  }
}