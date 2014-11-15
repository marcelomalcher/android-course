package br.rio.puc.lac.android.course;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class TestandoPermissaoActivity extends Activity {
    
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final EditText editText = (EditText) findViewById(R.id.editText1);
        
        Button button = (Button) findViewById(R.id.button1);
        
        final WebView webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   String endereco = editText.getText().toString(); 
			   Log.d("LAC", "Clique do botão com: " + endereco);
			   webView.loadUrl(endereco);			   
			}
		});                             
    }
	
	private class HelloWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
}
