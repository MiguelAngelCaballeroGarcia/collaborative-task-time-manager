package edu.ub.pis2425.mvvmexample.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.mvvmexample.domain.Client;
import edu.ub.pis2425.mvvmexample.data.services.AuthenticationService;
import edu.ub.pis2425.mvvmexample.utils.livedata.StateLiveData;

public class LogInViewModel extends ViewModel {
  /* Attributes */
  private final AuthenticationService authenticationService;
  /* LiveData */
  private final StateLiveData<Client> logInState;

  /* Constructor */
  public LogInViewModel() {
    authenticationService = new AuthenticationService();
    logInState = new StateLiveData<>();
  }
  /**
   * Returns the state of the log-in
   * @return the state of the log-in
   */
  public StateLiveData<Client> getLogInState() {
    return logInState;
  }

  /**
   * Logs in the user
   * @param username the username
   * @param password the password
   */
  public void logIn(String username, String password) {
    authenticationService.logIn(
      username,
      password,
      new AuthenticationService.OnLogInListener() {
        @Override
        public void onLogInSuccess(Client client) {
          logInState.postSuccess(client);
        }
        @Override
        public void onLogInError(Throwable throwable) {
          logInState.postError(throwable);
        }
      }
    );
  }
}
