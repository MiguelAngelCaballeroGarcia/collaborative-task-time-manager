package edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Client;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.LogInUseCase;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ClientPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.mappers.DomainToPOMapper;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.livedata.StateLiveData;

public class LogInViewModel extends ViewModel {
  /* Use cases */
  private final LogInUseCase logInUseCase;
  /* LiveData */
  private final StateLiveData<ClientPO> logInState;

  /* Constructor */
  public LogInViewModel(LogInUseCase logInUseCase) {
    this.logInUseCase = logInUseCase;
    logInState = new StateLiveData<>();
  }
  /**
   * Returns the state of the log-in
   * @return the state of the log-in
   */
  public StateLiveData<ClientPO> getLogInState() {
    return logInState;
  }

  /**
   * Logs in the user
   * @param username the username
   * @param password the password
   */
  public void logIn(String username, String password) {
    logInUseCase.execute(
      username,
      password,
      new LogInUseCase.OnLogInListener() {
        @Override
        public void onSuccess(Client client) {
          logInState.postSuccess(DomainToPOMapper.mapObject(client, ClientPO.class));
        }
        @Override
        public void onError(Throwable throwable) {
          logInState.postError(throwable);
        }
      }
    );
  }
}
