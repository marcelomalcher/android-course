package br.rio.puc.lac.android.course;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.second);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		
		String text = getIntent().getExtras().getString("TEXTO");
		
		tv.setText(text);
	}

}

