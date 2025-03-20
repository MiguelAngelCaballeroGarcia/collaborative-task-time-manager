package edu.ub.pis2425.mvvmexample.domain;

import java.util.HashMap;
import java.util.Map;

public class Client {
  /* Attributes */
  private String id; // email, basically
  private String username;
  private String password;
  private Map<String,Integer> cart; // map of <product.id, quantity>

  /**
   * Constructor.
   * @param id The id of the client.
   * @param password The password of the client.
   */
  public Client(String id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.cart = new HashMap<>();
  }

  /**
   * Empty constructor.
   */
  @SuppressWarnings("unused")
  public Client() { }

  /**
   * Gets the id of the client.
   * @return The id of the client.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the username of the client.
   * @return The username of the client.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the password of the client.
   * @return The password of the client.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Adds a purchase to the client.
   * @param productId The id of the product to be purchased.
   */
  public void addProductToCart(String productId, Integer addedQuantity) {
    if (!cart.containsKey(productId)) {
      cart.put(productId, addedQuantity);
      return;
    }

    Integer currentQuantity = cart.get(productId);
    cart.put(productId, currentQuantity + addedQuantity);
  }
}
