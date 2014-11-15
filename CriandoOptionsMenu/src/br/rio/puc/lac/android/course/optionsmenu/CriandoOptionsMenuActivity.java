package br.rio.puc.lac.android.course.optionsmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class CriandoOptionsMenuActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) { 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {  
		switch (item.getItemId()) { 
		case R.id.novo:
			Toast.makeText(CriandoOptionsMenuActivity.this, "Clicou em NOVO", 53000).show();
			return true; 
		case R.id.ajuda:
			Toast.makeText(CriandoOptionsMenuActivity.this, "Clicou em AJUDA", 3000).show();
			return true;
		case R.id.outra:
			Toast.makeText(CriandoOptionsMenuActivity.this, "Clicou em OUTRA", 3000).show();
			return true;	    	  
		default:
			return super.onOptionsItemSelected(item);
		}
	} 


}