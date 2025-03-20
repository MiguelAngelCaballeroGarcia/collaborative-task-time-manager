package edu.ub.pis2425.cleanarchitectureexample;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import edu.ub.pis2425.cleanarchitectureexample.data.repositories.firestore.ClientFirestoreRepository;
import edu.ub.pis2425.cleanarchitectureexample.data.repositories.firestore.ProductFirestoreRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ClientRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.repositories.ProductRepository;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetAllProductsUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetProductsByNameUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.LogInUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.RemoveProductUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.SignUpUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation.GetAllProductsUseCaseImpl;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation.GetProductsByNameUseCaseImpl;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation.LogInUseCaseImpl;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation.RemoveProductUseCaseImpl;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.implementation.SignUpUseCaseImpl;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.LogInViewModel;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.ProfileViewModel;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.ShoppingViewModel;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.SignUpViewModel;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.ViewProductDetailsViewModel;

public class AppContainer {
  private Application application;

  /* Dependency injection */
  // Repositories
  private final ClientRepository clientRepository = new ClientFirestoreRepository();
  private final ProductRepository productRepository = new ProductFirestoreRepository();
  // Use cases
  private final LogInUseCase logInUseCase = new LogInUseCaseImpl(clientRepository);
  private final SignUpUseCase signUpUseCase = new SignUpUseCaseImpl(clientRepository);
  private final GetAllProductsUseCase getAllProductsUseCase = new GetAllProductsUseCaseImpl(productRepository);
  private final GetProductsByNameUseCase getProductsByNameUseCase = new GetProductsByNameUseCaseImpl(productRepository);
  private final RemoveProductUseCase removeProductUseCase = new RemoveProductUseCaseImpl(productRepository);
  // ViewModel factory: initialization of internal class (see below)
  public ViewModelFactory viewModelFactory = new ViewModelFactory(this);

  /* Internal class definitions */
  // ViewModel factory
  public static class ViewModelFactory implements ViewModelProvider.Factory {
    private final AppContainer appContainer;

    public ViewModelFactory(AppContainer appContainer) {
      this.appContainer = appContainer;
    }

    @SuppressWarnings("unchecked") // This would be helpful for lint warnings for casts.
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
      if (modelClass.isAssignableFrom(SignUpViewModel.class)) {
        return (T) new SignUpViewModel(appContainer.signUpUseCase);
      } else if (modelClass.isAssignableFrom(LogInViewModel.class)) {
        return (T) new LogInViewModel(appContainer.logInUseCase);
      } else if (modelClass.isAssignableFrom(ShoppingViewModel.class)) {
        return (T) new ShoppingViewModel(
            appContainer.getAllProductsUseCase,
            appContainer.getProductsByNameUseCase,
            appContainer.removeProductUseCase
        );
      } else if (modelClass.isAssignableFrom(ViewProductDetailsViewModel.class)) {
        return (T) new ViewProductDetailsViewModel(appContainer.getApplication());
      } else if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
        return (T) new ProfileViewModel(appContainer.getApplication());
      }
      throw new IllegalArgumentException("ViewModel Not Found");
    }
  }

  public AppContainer(Application application) {
    this.application = application;
  }

  public Application getApplication() {
    return application;
  }
}
