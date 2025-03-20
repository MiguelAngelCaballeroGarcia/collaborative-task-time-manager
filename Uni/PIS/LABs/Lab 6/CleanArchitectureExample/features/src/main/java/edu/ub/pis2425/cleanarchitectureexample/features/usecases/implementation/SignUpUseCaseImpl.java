package edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation;


import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Client;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ClientRepository;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ClientId;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.SignUpUseCase;

/**
 * Authentication service using a ClientRepository.
 */
public class SignUpUseCaseImpl implements SignUpUseCase {
  /* Attributes */
  private final ClientRepository clientRepository;

  /**
   * Empty constructor
   */
  @SuppressWarnings("unused")
  public SignUpUseCaseImpl(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  /**
   * Sign up a new client.
   * @param username the username
   * @param password the password
   * @param passwordConfirmation the password confirmation
   * @param listener the listener
   */
  public void execute(
      String username,
      String password,
      String passwordConfirmation,
      OnSignUpListener listener
  ) {
    if (username.isEmpty())
      listener.onError(new Throwable("Username cannot be empty"));
    else if (password.isEmpty())
      listener.onError(new Throwable("Password cannot be empty"));
    else if (passwordConfirmation.isEmpty())
      listener.onError(new Throwable("Password confirmation cannot be empty"));
    else if (!password.equals(passwordConfirmation))
      listener.onError(new Throwable("Passwords do not match"));
    else {
      Client client = new Client(new ClientId(username), null, password, null);
      clientRepository.add(client, new ClientRepository.Callback<Void>() {
        @Override
        public void onSuccess(Void result) {
          listener.onSuccess();
        }
        @Override
        public void onError(Throwable error) {
          listener.onError(error);
        }
      });
    }
  }
}
