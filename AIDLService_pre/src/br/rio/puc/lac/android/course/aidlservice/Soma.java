package br.rio.puc.lac.android.course.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

// TAREFA - Implemente a interface Parcelable
public class Soma {

  public long first;
  public long second;

  public static final Parcelable.Creator<Soma> CREATOR =
    new Parcelable.Creator<Soma>() {
      public Soma createFromParcel(Parcel in) {
        return new Soma(in);
      }

      public Soma[] newArray(int size) {
        return new Soma[size];
      }
    };

  public Soma() {
  }

  private Soma(Parcel in) {
    readFromParcel(in);
  }

  public void readFromParcel(Parcel in) {
    //TAREFA - Recuperar valores para atributos first e second
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    //TAREFA - Utilizar objeto Parcel e escrever  os atributos first e second
  }

  @Override
  public int describeContents() {
    return 0;
  }

}
