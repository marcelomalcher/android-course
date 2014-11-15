package br.rio.puc.lac.android.course.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onClick(View v) {
    if (v.getId() == R.id.button1) {
      Toast toast =
        Toast.makeText(getApplicationContext(), "Exemplo de Toast",
          Toast.LENGTH_SHORT);
      toast.show();
    }
    else if (v.getId() == R.id.button2) {
      Toast toast =
        Toast.makeText(getApplicationContext(),
          "Exemplo de Toast - posição customizada", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
      toast.show();
    }
    else if (v.getId() == R.id.button3) {
      LayoutInflater inflater = getLayoutInflater();
      View layout =
        inflater.inflate(R.layout.toast,
          (ViewGroup) findViewById(R.id.toast_layout_root));

      ImageView image = (ImageView) layout.findViewById(R.id.image);
      image.setImageResource(R.drawable.ic_launcher);
      TextView text = (TextView) layout.findViewById(R.id.text);
      text.setText("Isto é um toast customizado!");

      Toast toast = new Toast(getApplicationContext());
      toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
      toast.setDuration(Toast.LENGTH_LONG);
      toast.setView(layout);
      toast.show();
    }
  }

}