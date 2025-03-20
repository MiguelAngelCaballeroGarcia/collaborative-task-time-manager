package edu.ub.pis2425.cleanarchitectureexample.domain.entities;

import java.io.Serializable;

import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.Price;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ProductId;

/**
 * DTO for a product.
 */
public class Product implements Serializable {
  /* Attributes */
  private ProductId id;
  private String name;
  private String nameLowerCase;
  private String description;
  private Price price;
  private String imageUrl;

  public Product(
      ProductId id,
      String name,
      String nameLowerCase,
      String description,
      Price price,
      String imageUrl
  ) {
    this.id = id;
    this.name = name;
    this.nameLowerCase = nameLowerCase;
    this.description = description;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  /**
   * Empty constructor required for Firestore.
   */
  @SuppressWarnings("unused")
  public Product() { }

  /* Getters */
  public ProductId getId() { return id; }
  public String getName() { return name; }
  public String getNameLowerCase() { return nameLowerCase; }
  public String getDescription() { return description; }
  public Price getPrice() { return price; }
  public String getImageUrl() { return imageUrl; }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Product product = (Product) obj;
    return id.equals(product.id);
  }
}
