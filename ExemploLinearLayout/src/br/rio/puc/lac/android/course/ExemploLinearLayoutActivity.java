package br.rio.puc.lac.android.course;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ExemploLinearLayoutActivity extends Activity {
	
	private LinearLayout l ; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        l = (LinearLayout)findViewById(R.id.linearLayoutMain);
    }
    
    public void onClick(View v) {
    	if (l.getOrientation() == LinearLayout.HORIZONTAL)
    		l.setOrientation(LinearLayout.VERTICAL);
    	else //VERTICAL
    		l.setOrientation(LinearLayout.HORIZONTAL);    			    	    	
    }
}

