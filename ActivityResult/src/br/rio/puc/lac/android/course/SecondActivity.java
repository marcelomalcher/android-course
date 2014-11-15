package br.rio.puc.lac.android.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends android.app.Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		final EditText et = (EditText) findViewById(R.id.etNome);
		
		Button ok = (Button) findViewById(R.id.btnOk);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent data = new Intent();
				data.putExtra("name", et.getText().toString());
				setResult(RESULT_OK, data);		
				finish();
			}
		});
		
	}

}
