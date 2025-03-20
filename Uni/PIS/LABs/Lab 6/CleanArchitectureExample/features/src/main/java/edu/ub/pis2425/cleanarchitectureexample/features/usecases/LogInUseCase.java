package edu.ub.pis2425.cleanarchitectureexample.features.usecases;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Client;

public interface LogInUseCase {
  interface OnLogInListener {
    void onSuccess(Client client);
    void onError(Throwable throwable);
  }

  void execute(String email, String password, OnLogInListener onLogInListener);
}
