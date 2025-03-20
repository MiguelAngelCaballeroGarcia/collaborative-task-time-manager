package edu.ub.pis2425.cleanarchitectureexample.features.usecases;

import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;

public interface GetAllProductsUseCase {
  interface OnGetProductsListener {
    void onSuccess(List<Product> products);
    void onError(Throwable throwable);
  }

  void execute(OnGetProductsListener listener);
}
