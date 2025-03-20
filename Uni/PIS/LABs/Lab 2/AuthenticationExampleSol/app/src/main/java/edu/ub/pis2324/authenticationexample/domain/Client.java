package edu.ub.pis2324.authenticationexample.domain;

import java.io.Serializable;

/**
 * Domain entity holding the data and the behavior of a client.
 */
public class Client {
  /* Attributes */
  private String id;
  private String username;
  private String password;
  private String photoUrl;

  /**
   * Constructor.
   * @param id The id of the client.
   * @param password The password of the client.
   */
  public Client(String id, String username, String password, String photoUrl) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.photoUrl = photoUrl;
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
  public String getUsername() {
    return username;
  }

  /**
   * Gets the password of the client.
   * @return The password of the client.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the photo URL of the client.
   * @return The photo URL of the client.
   */
  public String getPhotoUrl() {
    return photoUrl;
  }
}
