package br.rio.puc.lac.android.course.googlemaps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends ItemizedOverlay {

  private ArrayList<OverlayItem> mOverlayItems = new ArrayList<OverlayItem>();
  Context mContext;

  public HelloItemizedOverlay(Drawable defaultMarker) {
    super(boundCenterBottom(defaultMarker));
  }

  public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
    super(boundCenterBottom(defaultMarker));
    mContext = context;
  }

  public void addOverlay(OverlayItem overlay) {
    mOverlayItems.add(overlay);
    populate();
  }

  @Override
  protected OverlayItem createItem(int i) {
    return mOverlayItems.get(i);
  }

  @Override
  public int size() {
    return mOverlayItems.size();
  }

  @Override
  protected boolean onTap(int index) {
    OverlayItem item = mOverlayItems.get(index);
    AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
    dialog.setTitle(item.getTitle());
    dialog.setMessage(item.getSnippet());
    dialog.show();
    return true;
  }

}