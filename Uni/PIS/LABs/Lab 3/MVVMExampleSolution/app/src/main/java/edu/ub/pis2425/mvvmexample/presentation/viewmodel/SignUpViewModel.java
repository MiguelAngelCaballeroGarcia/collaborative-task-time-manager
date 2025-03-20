package edu.ub.pis2425.mvvmexample.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.mvvmexample.data.services.AuthenticationService;
import edu.ub.pis2425.mvvmexample.utils.livedata.StateLiveData;

public class SignUpViewModel extends ViewModel {
  /* Attributes */
  private static AuthenticationService authenticationService;
  /* LiveData */
  private final StateLiveData<Void> signUpState;

  /* Constructor */
  public SignUpViewModel() {
    super();
    authenticationService = new AuthenticationService();
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
    authenticationService.signUp(
      username,
      password,
      passwordConfirmation,
      new AuthenticationService.OnSignUpListener() {
        @Override
        public void onSignUpSuccess() {
          signUpState.postSuccess(null);
        }
        @Override
        public void onSignUpError(Throwable throwable) {
          signUpState.postError(throwable);
        }
      }
    );
  }
}
