package edu.ub.pis2425.cleanarchitectureexample.features.usecases;

public interface SignUpUseCase {
  interface OnSignUpListener {
    void onSuccess();
    void onError(Throwable throwable);
  }

  void execute(String email, String password, String passwordConfirmation, OnSignUpListener onSignUpListener);
}
