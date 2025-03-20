package edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects;

import java.io.Serializable;

/**
 * DTO for a price.
 */
public class Price implements Serializable {
  private static final String DEFAULT_CURRENCY = "€";

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

  @SuppressWarnings("unused")
  public Price(Double amount) {
    this(amount, DEFAULT_CURRENCY);
  }

  @SuppressWarnings("unused")
  public Price() {
    this(0.0, DEFAULT_CURRENCY);
  }

  /* Getters */
  public Double getAmount() { return amount; }
  public String getCurrency() { return currency; }

  public Price add(Price price) {
    return new Price(amount + price.amount, currency);
  }
  public Price multiply(int quantity) {
    return new Price(amount * quantity, currency);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Price price = (Price) obj;
    return amount.equals(price.amount)
        && currency.equals(price.currency);
  }

  @Override
  public String toString() {
    return String.format("%.2f %s", amount, currency);
  }
}
