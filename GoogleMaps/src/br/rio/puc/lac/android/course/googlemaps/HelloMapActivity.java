package br.rio.puc.lac.android.course.googlemaps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.OverlayItem;

public class HelloMapActivity extends MapActivity {

  private MapView mapView;

  private MyLocationOverlay myLocationOverlay;

  private HelloItemizedOverlay itemizedoverlay;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);

    myLocationOverlay = new MyLocationOverlay(this, mapView);
    mapView.getOverlays().add(myLocationOverlay);

    myLocationOverlay.enableCompass();
    myLocationOverlay.enableMyLocation();
    myLocationOverlay.runOnFirstFix(new Runnable() {
      public void run() {
        mapView.getController().animateTo(myLocationOverlay.getMyLocation());
      }
    });

    itemizedoverlay =
      new HelloItemizedOverlay(
        this.getResources().getDrawable(R.drawable.flag), this);
    GeoPoint point = new GeoPoint(-22911915, -43192062);
    OverlayItem overlayitem = new OverlayItem(point, "Test", "First!!!");
    itemizedoverlay.addOverlay(overlayitem);
    mapView.getOverlays().add(itemizedoverlay);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = new MenuInflater(this);
    inflater.inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menuAdd) {
      GeoPoint point = mapView.getMapCenter();
      OverlayItem overlayitem = new OverlayItem(point, "Test", "Android");
      itemizedoverlay.addOverlay(overlayitem);
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }

  @Override
  protected void onResume() {
    super.onResume();
    myLocationOverlay.enableMyLocation();
    myLocationOverlay.enableCompass();
  }

  @Override
  protected void onPause() {
    super.onResume();
    myLocationOverlay.disableMyLocation();
    myLocationOverlay.disableCompass();
  }
}