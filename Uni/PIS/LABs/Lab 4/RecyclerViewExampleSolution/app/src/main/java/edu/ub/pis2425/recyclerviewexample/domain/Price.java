package edu.ub.pis2425.recyclerviewexample.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {
  /* Attributes */
  private Double amount;
  private String currency;

  /**
   * Constructor.
   * @param amount The price's amount.
   * @param currency The price's currency.
   */
  public Price(Double amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  /**
   * Empty constructor required for Firestore.
   */
  @SuppressWarnings("unused")
  public Price() { }

  /* Getters */
  public Double getAmount() { return amount; }
  public String getCurrency() { return currency; }

  @Override
  public String toString() {
    return String.format("%.2f %s", amount, currency);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(this.amount);
    dest.writeString(this.currency);
  }

  public void readFromParcel(Parcel source) {
    this.amount = (Double) source.readValue(Double.class.getClassLoader());
    this.currency = source.readString();
  }

  protected Price(Parcel in) {
    this.amount = (Double) in.readValue(Double.class.getClassLoader());
    this.currency = in.readString();
  }

  public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
    @Override
    public Price createFromParcel(Parcel source) {
      return new Price(source);
    }

    @Override
    public Price[] newArray(int size) {
      return new Price[size];
    }
  };
}
