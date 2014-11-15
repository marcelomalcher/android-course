package br.rio.puc.lac.android.course.adapterlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExemploArrayAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;

	public ExemploArrayAdapter(Context context, String[] values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.label);

		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

		textView.setText(values[position]);

		String s = values[position];

		switch (position) {
		case (0): //Brasil
			imageView.setImageResource(R.drawable.brasil);
			break ;
		case (1): //EUA
			imageView.setImageResource(R.drawable.eua);
			break ;
		case  (2): //França
			imageView.setImageResource(R.drawable.franca);
			break ;
		case  (3): //Japão
			imageView.setImageResource(R.drawable.japao);
			break ;
		case (4): //Russia
			imageView.setImageResource(R.drawable.russia);
			break ;
		}

		return rowView;
	}
}
