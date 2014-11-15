package br.rio.puc.lac.android.course;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText et = (EditText) findViewById(R.id.editText1);
        
        final TextView tv = (TextView)findViewById(R.id.textview1);
        
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				tv.setText(et.getText().toString());				
			}
		});
    }
}