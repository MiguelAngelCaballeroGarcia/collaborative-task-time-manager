package edu.ub.pis2425.cleanarchitectureexample;

import android.app.Application;

/**
 * To be able to perform manual dependency injection.
 * Source:
 * https://developer.android.com/training/dependency-injection/manual
 */
public class MyApplication extends Application {

  public AppContainer appContainer;

  @Override
  public void onCreate() {
    super.onCreate();
    // Initialize the AppContainer when the application starts
    appContainer = new AppContainer(this);
  }

  @SuppressWarnings("unused")
  public AppContainer getAppContainer() {
    return appContainer;
  }

  @SuppressWarnings("unused")
  public AppContainer.ViewModelFactory getViewModelFactory() {
    return appContainer.viewModelFactory;
  }
}
