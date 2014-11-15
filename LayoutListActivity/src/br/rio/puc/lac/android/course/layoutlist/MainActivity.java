package br.rio.puc.lac.android.course.layoutlist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.rowlayout, R.id.label, getResources().getStringArray(R.array.paises));
		
		setListAdapter(adapter);
	}
}