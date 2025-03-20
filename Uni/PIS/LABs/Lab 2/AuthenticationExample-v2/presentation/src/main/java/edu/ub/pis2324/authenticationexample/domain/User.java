package edu.ub.pis2324.authenticationexample.domain;

import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidEmailException;
import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidPasswordException;
import edu.ub.pis2324.authenticationexample.domain.exceptions.InvalidUsernameException;

/**
 * Domain entity holding the data and the behavior of a client.
 */
public class User {
  /* Attributes */
  private Username username;
  private Email email;
  private Password password;

  /**
   * Constructor.
   * @param username The username of the client.
   * @param email The email of the client.
   * @param password The password of the client.
   */
  public User(String username, String email, String password) throws InvalidUsernameException, InvalidEmailException, InvalidPasswordException {
      this.username = new Username(username);
      this.email = new Email(email);
      this.password = new Password(password);
  }

  /**
   * Gets the username of the client.
   * @return The username of the client.
   */
  public Username getUsername() {
    return username;
  }

  /**
   * Gets the email of the client.
   * @return The email of the client.
   */
  public Email getEmail() {
    return email;
  }

  /**
   * Gets the password of the client.
   * @return The password of the client.
   */
  public Password getPassword() {
    return password;
  }
}
