package edu.ub.pis2425.cleanarchitectureexample.data.dtos.firestore;

import java.util.Map;

import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.LineItem;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ProductId;

/**
 * Domain entity holding the data and the behavior of a client.
 */
public class CartFirestoreDto {
  /* Attributes */
  private Map<String, LineItem<ProductId>> cartLines; // <productId, quantity>

  /**
   * Empty constructor.
   */
  @SuppressWarnings("unused")
  public CartFirestoreDto() { }

  public Map<String, LineItem<ProductId>> getCartLines() {
    return cartLines;
  }
}
