package edu.ub.pis2425.cleanarchitectureexample.features.usecases;

public interface RemoveProductUseCase {
  interface OnRemoveProductListener {
    void onSuccess();
    void onError(Throwable throwable);
  }

  void execute(String productId, OnRemoveProductListener listener);
}
