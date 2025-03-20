package edu.ub.pis2425.cleanarchitectureexample.features.repositories;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Client;

public interface ClientRepository {
   interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable error);
  }

  void add(Client client, Callback<Void> callback);
  void getById(String id, Callback<Client> callback);
}
