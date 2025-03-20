package edu.ub.pis2425.mvvmexample.data.services;

import java.util.ArrayList;
import java.util.List;

import edu.ub.pis2425.mvvmexample.domain.Product;
import edu.ub.pis2425.mvvmexample.data.services.mockdata.MockProductsHashMap;

/**
 * Cloud Firestore implementation of the data store.
 */
public class ProductStoreService {
  /* Attributs */
  private static MockProductsHashMap products;
  static { products = new MockProductsHashMap(); }

  public interface OnFetchProductsListener {
    void onFetchProducts(List<Product> products);
  }

  /**
   * Empty constructor
   */
  public ProductStoreService() { }

  /**
   * Fetches all the products from the data store.
   * @return a list of products
   */
  public void getAll(OnFetchProductsListener listener) {
    listener.onFetchProducts(new ArrayList<>(products.values()));
  }

  /**
   * Fetches a product by its id from the data store.
   * @param name the name of the product
   * @return a list of product
   */
  public void getByName(String name, OnFetchProductsListener listener) {
    List<Product> result = new ArrayList<>();
    for (Product product : products.values()) {
      if (product.getNameLowerCase().contains(name)) {
        result.add(product);
      }
    }
    listener.onFetchProducts(result);
  }
}
