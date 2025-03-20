package edu.ub.pis2425.cleanarchitectureexample.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import edu.ub.pis2425.cleanarchitectureexample.MyApplication;
import edu.ub.pis2425.cleanarchitectureexample.databinding.FragmentSignUpBinding;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.SignUpViewModel;

public class SignUpFragment extends Fragment {
  /* Constants */
  public static final String TAG = "SignUpFragment";
  /* ViewModel */
  private SignUpViewModel signUpViewModel;

  private NavController navController;
  private FragmentSignUpBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentSignUpBinding.inflate(inflater, container, false);

    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null)
      actionBar.show();

    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    navController = Navigation.findNavController(view);

    /* Initializations */
    initWidgetListeners();
    initViewModel();
  }

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    binding.btnSignUp.setOnClickListener(ignoredView -> {
      signUpViewModel.signUp(
          String.valueOf(binding.etSignupUsername.getText()),
          String.valueOf(binding.etSignupPassword.getText()),
          String.valueOf(binding.etSignupPasswordConfirmation.getText())
      );
    });
  }

  /**
   * Initialize the viewmodel and its observers.
   */
  private void initViewModel() {
    /* Access the app container for dependency injection */

    /* Init viewmodel */
    signUpViewModel = new ViewModelProvider(
        this,
        ((MyApplication) requireActivity().getApplication()).getViewModelFactory()
    ).get(SignUpViewModel.class);

    /* Init observers */
    initObservers();
  }

  /**
   * Initialize the observers of the viewmodel.
   */
  private void initObservers() {
    signUpViewModel.getSignUpState().observe(getViewLifecycleOwner(), state -> {
      switch (state.getStatus()) {
        case COMPLETE:
          navController.navigateUp();
          break;
        case ERROR:
          Toast.makeText(getActivity(), state.getError().getMessage(), Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }
}