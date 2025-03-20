package edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public class ProductId implements Serializable {
  private String id;

  public ProductId(String id) {
    this.id = id;
  }

  @SuppressWarnings("unused")
  public ProductId() {}

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ProductId productId = (ProductId) obj;
    return Objects.equals(id, productId.id); // Use Objects.equals for null safety
  }

  @Override
  public int hashCode() {
    return Objects.hash(id); // Use Objects.hash to generate hash code based on id
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
