package edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation;


import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ProductRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.RemoveProductUseCase;


/**
 * Authentication service using a ClientRepository.
 */
public class RemoveProductUseCaseImpl implements RemoveProductUseCase {
  /* Attributes */
  private final ProductRepository productRepository;

  /**
   * Empty constructor
   */
  @SuppressWarnings("unused")
  public RemoveProductUseCaseImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Get all products.
   * @param listener the listener
   */
  public void execute(String id, OnRemoveProductListener listener) {
    productRepository.remove(id, new ProductRepository.Callback<>() {
      @Override
      public void onSuccess(Void ignored) {
        listener.onSuccess();
      }

      @Override
      public void onError(Throwable error) {
        listener.onError(error);
      }
    });
  }
}
