package br.rio.puc.lac.android.course.relativelayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ExemploRelativeLayoutActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.main);
        
        EditText entry = (EditText) findViewById(R.id.entry);
        
        entry.setText("LAC PUC-Rio");
        
    }
    
    
}
