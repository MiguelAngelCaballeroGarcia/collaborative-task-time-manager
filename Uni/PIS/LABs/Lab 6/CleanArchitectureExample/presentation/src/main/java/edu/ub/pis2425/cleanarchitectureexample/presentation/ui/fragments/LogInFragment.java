package edu.ub.pis2425.cleanarchitectureexample.presentation.ui.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import edu.ub.pis2425.cleanarchitectureexample.MyApplication;
import edu.ub.pis2425.cleanarchitectureexample.R;
import edu.ub.pis2425.cleanarchitectureexample.databinding.FragmentLogInBinding;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.LogInViewModel;

public class LogInFragment extends Fragment {

  private LogInViewModel logInViewModel;
  private FragmentLogInBinding binding;
  private NavController navController;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentLogInBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    navController = Navigation.findNavController(view);

    /* Initializations */
    initWidgetListeners();
    initViewModel();}

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    binding.btnLogIn.setOnClickListener(ignoredView -> {
      // Delegate the log-in logic to the viewmodel
      logInViewModel.logIn(
          String.valueOf(binding.etLoginUsername.getText()),
          String.valueOf(binding.etLoginPassword.getText())
      );
    });

    binding.btnSignUp.setOnClickListener(ignoredView -> {
      navController.navigate(R.id.action_logInFragment_to_signUpFragment);
    });
  }

  /**
   * Initialize the viewmodel and its observers.
   */
  private void initViewModel() {
    /* Init viewmodel */
    logInViewModel = new ViewModelProvider(
        this,
        ((MyApplication) requireActivity().getApplication()).getViewModelFactory()
    ).get(LogInViewModel.class);

    /* Init observers */
    initObservers();
  }

  /**
   * Initialize the observers of the viewmodel.
   */
  private void initObservers() {
    /* Observe the login state */
    logInViewModel.getLogInState().observe(getViewLifecycleOwner(), logInState -> {
      // Whenever there's a change in the login state of the viewmodel
      switch (logInState.getStatus()) {
        case LOADING:
          binding.btnLogIn.setEnabled(false);
          break;
        case SUCCESS:
          assert logInState.getData() != null;
          SharedPreferences.Editor editor = requireActivity().getSharedPreferences("LOGIN", MODE_PRIVATE).edit();
          String clientModelJsonString = new Gson().toJson(logInState.getData());
          editor.putString("CLIENT_MODEL", clientModelJsonString);
          editor.apply();
          navController.navigate(R.id.action_logInFragment_to_shoppingFragment);
          break;
        case ERROR:
          assert logInState.getError() != null;
          String errorMessage = logInState.getError().getMessage();
          Toast.makeText(this.getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
          binding.btnLogIn.setEnabled(true);
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + logInState.getStatus());
      }
    });
  }
}