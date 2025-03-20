package edu.ub.pis2425.cleanarchitectureexample.presentation.pos;


import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.Price;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.PricedLineItem;

public class CartPO {
  private final List<PricedLineItem<ProductPO>> pricedCartLines;
  private final Price priceTotal;

  public CartPO(List<PricedLineItem<ProductPO>> pricedCartLines, Price priceTotal) {
    this.pricedCartLines = pricedCartLines;
    this.priceTotal = priceTotal;
  }

  public List<PricedLineItem<ProductPO>> getCartLineModels() {
    return pricedCartLines;
  }

  public Price getPriceTotal() {
    return priceTotal;
  }
}
