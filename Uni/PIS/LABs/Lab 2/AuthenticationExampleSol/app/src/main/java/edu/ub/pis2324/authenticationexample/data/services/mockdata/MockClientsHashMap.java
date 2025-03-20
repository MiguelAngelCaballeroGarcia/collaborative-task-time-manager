package edu.ub.pis2324.authenticationexample.data.services.mockdata;

import java.util.HashMap;

import edu.ub.pis2324.authenticationexample.domain.Client;

public class MockClientsHashMap extends HashMap<String, Client> {
  /**
   * Empty constructor
   */
  public MockClientsHashMap() {
    super();
    mockInit();
  }

  /**
   * Initializes the mock data
   */
  private void mockInit() {
    put("admin", new Client("admin", "admin", "admin", "https://itkonekt.com/media/2019/01/Uncle_Bob_400x400.png"));
    put("jcena65@gmail.com", new Client("jcena65@gmail.com", "jcena", "password", "https://pbs.twimg.com/profile_images/458716943902461954/vF2vEzY8_400x400.jpeg"));
  }
}
