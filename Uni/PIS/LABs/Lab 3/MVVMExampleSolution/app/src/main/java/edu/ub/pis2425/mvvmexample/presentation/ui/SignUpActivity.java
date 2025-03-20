package edu.ub.pis2425.mvvmexample.presentation.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import edu.ub.pis2425.mvvmexample.databinding.ActivitySignUpBinding;
import edu.ub.pis2425.mvvmexample.presentation.viewmodel.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
  /* Attributes */

  /* ViewModel */
  private SignUpViewModel signUpViewModel;
  /* View binding */
  private ActivitySignUpBinding binding;

  /**
   * Called when the activity is being created.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySignUpBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

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
    signUpViewModel = new ViewModelProvider(
        this
    ).get(SignUpViewModel.class);

    initObservers();
  }

  /**
   * Initialize the observers of the viewmodel.
   */
  private void initObservers() {
    signUpViewModel.getSignUpState().observe(this, state -> {
      switch (state.getStatus()) {
        case SUCCESS:
          finish();
          break;
        case ERROR:
          Toast.makeText(this, state.getError().getMessage(), Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }
}