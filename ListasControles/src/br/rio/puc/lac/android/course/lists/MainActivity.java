package br.rio.puc.lac.android.course.lists;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListAdapter(new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1, 
        		getResources().getStringArray(R.array.paises)));
    }
}