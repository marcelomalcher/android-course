package br.rio.puc.lac.android.course.usegps;

import android.app.Activity;
import android.content.Context;
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

    locationManager =
      (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    provider = LocationManager.GPS_PROVIDER;

    Location location = locationManager.getLastKnownLocation(provider);

    if (location != null) {
      System.out.println("Provider " + provider + " has been selected.");
      int lat = (int) (location.getLatitude());
      int lng = (int) (location.getLongitude());
      latituteField.setText(String.valueOf(lat));
      longitudeField.setText(String.valueOf(lng));
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    locationManager.requestLocationUpdates(provider, 0, 0, this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    locationManager.removeUpdates(this);
  }

  @Override
  public void onLocationChanged(Location location) {
    int lat = (int) (location.getLatitude());
    int lng = (int) (location.getLongitude());
    latituteField.setText(String.valueOf(lat));
    longitudeField.setText(String.valueOf(lng));
    Toast.makeText(this, "Nova localização - lat " + lat + " lon " + lng, 3000)
      .show();
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