package br.rio.puc.lac.android.course.sqlite;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ContatoArrayAdapter extends ArrayAdapter<Contato> {

  private final Context context;
  private final List<Contato> contatos;

  public ContatoArrayAdapter(Context context, List<Contato> contatos) {
    super(context, R.layout.contatorow, contatos);
    this.context = context;
    this.contatos = contatos;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater =
      (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View rowView = inflater.inflate(R.layout.contatorow, parent, false);

    //Obter a referência ao objeto Contato correspondente a posição da lista
    //e com este objeto exibir os atributos nas posições correspondentes do layout

    return rowView;
  }
}
