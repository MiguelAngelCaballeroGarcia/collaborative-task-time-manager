package edu.ub.pis2324.authenticationexample.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.ub.pis2324.authenticationexample.databinding.ActivityLogInBinding;
import edu.ub.pis2324.authenticationexample.domain.Client;
import edu.ub.pis2324.authenticationexample.data.services.AuthenticationService;

public class LogInActivity extends AppCompatActivity {
  /* Attributes */
  private AuthenticationService authService;
  /* View binding */
  private ActivityLogInBinding binding;

  /**
   * Called when the activity is being created.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* Set view binding */
    binding = ActivityLogInBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    /* Initializations */
    authService = new AuthenticationService();
    initWidgetListeners();
  }

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    binding.btnLogIn.setOnClickListener(ignoredView -> {
      /* Get the values of the widgets */
      String username = binding.etLoginUsername.getText().toString();
      String password = binding.etLoginPassword.getText().toString();

      /* Define the listener for the log-in operation */
      AuthenticationService.OnLogInListener listener;
      listener = new AuthenticationService.OnLogInListener() {
        @Override
        public void onLogInSuccess(Client client) {
          Intent intent = new Intent(
              LogInActivity.this,
              LoggedInActivity.class
          );
          intent.putExtra("CLIENT_ID", client.getId());
          intent.putExtra("CLIENT_PHOTO_URL", client.getPhotoUrl());
          startActivity(intent);
          finish();
        }
        @Override
        public void onLogInError(Throwable throwable) {
          String errorMessage = throwable.getMessage();
          Toast.makeText(
              LogInActivity.this,
              errorMessage,
              Toast.LENGTH_SHORT
          ).show();
        }
      };

      /* Invoke the log-in operation */
      authService.logIn(username, password, listener);
    });

    binding.btnSignUp.setOnClickListener(ignoredView -> {
      Intent intent = new Intent(this, SignUpActivity.class);
      startActivity(intent);
    });
  }
}