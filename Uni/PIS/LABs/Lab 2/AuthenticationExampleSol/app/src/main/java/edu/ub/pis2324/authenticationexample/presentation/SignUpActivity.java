package edu.ub.pis2324.authenticationexample.presentation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.ub.pis2324.authenticationexample.databinding.ActivitySignUpBinding;
import edu.ub.pis2324.authenticationexample.data.services.AuthenticationService;

public class SignUpActivity extends AppCompatActivity {
  /* Attributes */
  private AuthenticationService authenticationService;
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

    /* Initializations */
    authenticationService = new AuthenticationService();
    initWidgetListeners();
  }

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    binding.btnConfirm.setOnClickListener(ignoredView -> {
      /* Get the values of the widgets */
      String username = binding.etSignupUsername.getText().toString();
      String password = binding.etSignupPassword.getText().toString();
      String passwordConfirmation = binding.etSignupPasswordConfirmation.getText().toString();

      /* Define the listener for the sign up operation */
      AuthenticationService.OnSignUpListener onSignUpListener = new AuthenticationService.OnSignUpListener() {
        @Override
        public void onSignUpSuccess() {
          finish();
        }
        @Override
        public void onSignUpError(Throwable throwable) {
          String errorMessage = throwable.getMessage();
          Toast.makeText(
              SignUpActivity.this,
              errorMessage,
              Toast.LENGTH_SHORT
          ).show();
        }
      };

      /* Invoke the sign up operation */
      authenticationService.signUp(
          username,
          password,
          passwordConfirmation,
          onSignUpListener
      );
    });
  }
}