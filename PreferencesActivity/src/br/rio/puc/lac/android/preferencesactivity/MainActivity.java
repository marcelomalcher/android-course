package br.rio.puc.lac.android.preferencesactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    final TextView tv = (TextView) findViewById(R.id.textView1);
    final CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);

    Button b = (Button) findViewById(R.id.button1);
    b.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, Preferences.class);
        startActivity(i);
      }
    });

    // Obtendo objeto de preferências
    SharedPreferences prefs =
      PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
    //
    tv.setText(prefs.getString("et", ""));
    cb.setChecked(prefs.getBoolean("cb", false));

    prefs
      .registerOnSharedPreferenceChangeListener(new OnSharedPreferenceChangeListener() {

        @Override
        public void onSharedPreferenceChanged(
          SharedPreferences sharedPreferences, String key) {
          if (key.equals("et")) {
            tv.setText(sharedPreferences
              .getString(key, tv.getText().toString()));
          }
          else if (key.equals("cb")) {
            cb.setChecked(sharedPreferences.getBoolean("cb", cb.isChecked()));
          }
        }
      });

  }
}