package edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation;


import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ProductRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetProductsByNameUseCase;


/**
 * Authentication service using a ClientRepository.
 */
public class GetProductsByNameUseCaseImpl implements GetProductsByNameUseCase {
  /* Attributes */
  private final ProductRepository productRepository;

  /**
   * Empty constructor
   */
  @SuppressWarnings("unused")
  public GetProductsByNameUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Get all products.
   * @param listener the listener
   */
  public void execute(String name, OnGetProductsByNameListener listener) {
    productRepository.getByName(name, new ProductRepository.Callback<>() {
      @Override
      public void onSuccess(List<Product> products) {
        listener.onSuccess(products);
      }

      @Override
      public void onError(Throwable error) {
        listener.onError(error);
      }
    });
  }
}
