package edu.ub.pis2425.cleanarchitectureexample.features.repositories;

import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;

public interface ProductRepository {
   interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable error);
  }

  void add(Product product, Callback<Boolean> callback);
  void getById(String id, Callback<Product> callback);
  void getAll(Callback<List<Product>> callback);
  void getByName(String name, Callback<List<Product>> callback);
  /* EXERCICI 2 */
  void remove(String id, Callback<Void> callback);
  // ...
}
