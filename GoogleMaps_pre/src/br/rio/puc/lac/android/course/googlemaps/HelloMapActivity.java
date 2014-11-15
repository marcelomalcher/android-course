package br.rio.puc.lac.android.course.googlemaps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class HelloMapActivity extends MapActivity {

  private MapView mapView;

  private MyLocationOverlay myLocationOverlay;

  private HelloItemizedOverlay itemizedOverlay;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    //TAREFA
    //- Recupere a referência para mapView
    //- Habilite os controles de zoom com o método setBuiltInZoomControles(true)
    //- Instancie o objeto myLocationOverlay e passe a referência ao objeto mapView    
    //- Adicione o myLocationOverlay à lista de overlays do mapView

    myLocationOverlay.enableCompass();
    myLocationOverlay.enableMyLocation();
    myLocationOverlay.runOnFirstFix(new Runnable() {
      public void run() {
        mapView.getController().animateTo(myLocationOverlay.getMyLocation());
      }
    });

    //TAREFA
    //- Obtenha no código referência ao arquivo de imagem 'flag.png' que está contido na pasta res/drawable-hpdi/flag.png
    //-- Para isto, utilize o método getResources().getDrawable()    
    //- Instancie o objeto itemizedOverlay passando como parâmetros o objeto Drawable obtido e a própria classe
    //- Instancie um objeto GeoPoint passando os valores -22911915 e -43192062 como latitude e longitude
    //- Instancie um novo OverlayItem utilizando este objeto GeoPoint
    //- Adicione ao objeto itemizedOverlay este OverlayItem
    //- Adicione o itemizedOverlay à lista de overlays do mapView
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
      //TAREFA 
      //- Obtenha o ponto central do mapa (GeoPoint)
      //- Instancie um novo objeto OverlayItem passando este ponto central como parâmetero
      //- Adicione este OverlayItem ao objeto itemizedOverlay
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