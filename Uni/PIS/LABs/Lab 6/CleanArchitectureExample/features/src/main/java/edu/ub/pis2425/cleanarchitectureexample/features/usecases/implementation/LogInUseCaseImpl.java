package edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation;


import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Client;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ClientRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.LogInUseCase;

/**
 * Authentication service using a ClientRepository.
 */
public class LogInUseCaseImpl implements LogInUseCase {
  /* Attributes */
  private final ClientRepository clientRepository;

  /**
   * Empty constructor
   */
  @SuppressWarnings("unused")
  public LogInUseCaseImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  /**
   * Log in client with username and password.
   * @param username the username
   * @param enteredPassword the password
   * @param listener the listener
   */
  public void execute(
      String username,
      String enteredPassword,
      OnLogInListener listener
  ) {
    if (username.isEmpty())
      listener.onError(new Throwable("Username cannot be empty"));
    else if (enteredPassword.isEmpty())
      listener.onError(new Throwable("Password cannot be empty"));
    else {
      clientRepository.getById(username, new ClientRepository.Callback<>() {
        @Override
        public void onSuccess(Client loggedClient) {
          if (!loggedClient.getPassword().equals(enteredPassword))
            listener.onError(new Throwable("Invalid credentials"));
          else
            listener.onSuccess(loggedClient);
        }
        @Override
        public void onError(Throwable error) {
          listener.onError(new Throwable("Invalid credentials"));
        }
      });
    }
  }
}
