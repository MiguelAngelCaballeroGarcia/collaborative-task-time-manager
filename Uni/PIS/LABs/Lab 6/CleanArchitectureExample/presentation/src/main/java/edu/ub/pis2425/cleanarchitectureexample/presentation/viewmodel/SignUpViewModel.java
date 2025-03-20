package edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.cleanarchitectureexample.features.usecases.SignUpUseCase;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.livedata.StateLiveData;

public class SignUpViewModel extends ViewModel {
  /* Use cases */
  private final SignUpUseCase signUpUseCase;
  /* LiveData */
  private final StateLiveData<Void> signUpState;

  /* Constructor */
  public SignUpViewModel(SignUpUseCase signUpUseCase) {
    super();
    this.signUpUseCase = signUpUseCase;
    signUpState = new StateLiveData<>();
  }

  /**
   * Returns the state of the sign-up
   * @return the state of the sign-up
   */
  public StateLiveData<Void> getSignUpState() {
    return signUpState;
  }

  /**
   * Signs up the user
   * @param username the username
   * @param password the password
   * @param passwordConfirmation the password confirmation
   */
  public void signUp(String username, String password, String passwordConfirmation) {
    signUpUseCase.execute(
      username,
      password,
      passwordConfirmation,
      new SignUpUseCase.OnSignUpListener() {
        @Override
        public void onSuccess() {
          signUpState.postSuccess(null);
        }
        @Override
        public void onError(Throwable throwable) {
          signUpState.postError(throwable);
        }
      }
    );
  }
}
