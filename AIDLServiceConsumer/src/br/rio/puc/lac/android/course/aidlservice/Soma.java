package br.rio.puc.lac.android.course.aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

public class Soma implements Parcelable {

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
    first = in.readLong();
    second = in.readLong();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(first);
    dest.writeLong(second);
  }

  @Override
  public int describeContents() {
    // TODO Auto-generated method stub
    return 0;
  }

}
