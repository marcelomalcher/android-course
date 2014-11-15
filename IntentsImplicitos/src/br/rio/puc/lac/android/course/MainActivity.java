package br.rio.puc.lac.android.course;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Uri uri = (Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM);
        
        ImageView iv = (ImageView) findViewById(R.id.imageView1);                
        
        iv.setImageURI(uri);
        
        TextView tv = (TextView) findViewById(R.id.textView1);
        
        
        tv.setText(uri.toString());
    }
}

