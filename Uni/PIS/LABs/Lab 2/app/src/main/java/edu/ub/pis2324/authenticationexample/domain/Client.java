package edu.ub.pis2324.authenticationexample.domain;

/**
 * Domain entity holding the data and the behavior of a client.
 */
public class Client {
  /* Attributes */
  private String id; // username
  private String email;
  private String password;

  /**
   * Constructor.
   * @param id The id of the client.
   * @param password The password of the client.
   */
  public Client(String id, String username, String password) {
    this.id = id;
    this.email = username;
    this.password = password;
  }

  /**
   * Empty constructor.
   */
  @SuppressWarnings("unused")
  public Client() { }

  /**
   * Gets the id of the client.
   * @return The id of the client.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the username of the client.
   * @return The username of the client.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the password of the client.
   * @return The password of the client.
   */
  public String getPassword() {
    return password;
  }
}
