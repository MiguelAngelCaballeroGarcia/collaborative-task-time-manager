package edu.ub.pis2425.mvvmexample.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
  /* Attributes */
  private String id;
  private String name;
  private String nameLowerCase;
  private String description;
  private Price price;
  private String imageUrl;
  private Boolean fragile;

  public Product(
      String id,
      String name,
      String nameLowerCase,
      String description,
      Price price,
      String imageUrl,
      Boolean fragile
  ) {
    this.id = id;
    this.name = name;
    this.nameLowerCase = nameLowerCase;
    this.description = description;
    this.price = price;
    this.imageUrl = imageUrl;
    this.fragile = fragile;
  }

  /**
   * Empty constructor required for Firestore.
   */
  @SuppressWarnings("unused")
  public Product() { }

  /* Getters */
  public String getId() { return id; }
  public String getName() { return name; }
  public String getNameLowerCase() { return nameLowerCase; }
  public String getDescription() { return description; }
  public Price getPrice() { return price; }
  public String getImageUrl() { return imageUrl; }
  public Boolean getFragile() { return fragile; }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.id);
    dest.writeString(this.name);
    dest.writeString(this.nameLowerCase);
    dest.writeString(this.description);
    dest.writeParcelable(this.price, flags);
    dest.writeString(this.imageUrl);
    dest.writeValue(this.fragile);
  }

  public void readFromParcel(Parcel source) {
    this.id = source.readString();
    this.name = source.readString();
    this.nameLowerCase = source.readString();
    this.description = source.readString();
    this.price = source.readParcelable(Price.class.getClassLoader());
    this.imageUrl = source.readString();
    this.fragile = (Boolean) source.readValue(Boolean.class.getClassLoader());
  }

  protected Product(Parcel in) {
    this.id = in.readString();
    this.name = in.readString();
    this.nameLowerCase = in.readString();
    this.description = in.readString();
    this.price = in.readParcelable(Price.class.getClassLoader());
    this.imageUrl = in.readString();
    this.fragile = (Boolean) in.readValue(Boolean.class.getClassLoader());
  }

  public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
    @Override
    public Product createFromParcel(Parcel source) {
      return new Product(source);
    }

    @Override
    public Product[] newArray(int size) {
      return new Product[size];
    }
  };
}
