package edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation;


import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ProductRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetAllProductsUseCase;


/**
 * Authentication service using a ClientRepository.
 */
public class GetAllProductsUseCaseImpl implements GetAllProductsUseCase {
  /* Attributes */
  private final ProductRepository productRepository;

  /**
   * Empty constructor
   */
  @SuppressWarnings("unused")
  public GetAllProductsUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Get all products.
   * @param listener the listener
   */
  public void execute(OnGetProductsListener listener) {
    productRepository.getAll(new ProductRepository.Callback<>() {
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
