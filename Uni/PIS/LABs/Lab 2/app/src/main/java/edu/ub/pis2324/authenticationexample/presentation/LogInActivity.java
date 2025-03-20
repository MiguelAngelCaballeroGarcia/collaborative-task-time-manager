package edu.ub.pis2324.authenticationexample.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import edu.ub.pis2324.authenticationexample.R;
import edu.ub.pis2324.authenticationexample.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {
  /* Attributes */

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
    //Log.d("LogInActivity", "El meu missatge de log.");

    /* Subrayar el "Regístrate" del TextView ForgotPassword */
    SpannableString spannableStringRegistre = new SpannableString(getString(R.string.not_signed_up));
    UnderlineSpan underlineSpanRegistre = new UnderlineSpan();

    int startIndexRegistre = getString(R.string.not_signed_up).indexOf("Regístrate");
    int endIndexRegistre = startIndexRegistre + "Regístrate".length();

    spannableStringRegistre.setSpan(
        underlineSpanRegistre,
        startIndexRegistre,
        endIndexRegistre,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    binding.NotSignedUp.setText(spannableStringRegistre);

    /* Hacer clicable el TextView ForgotPassword */
    ClickableSpan clickableSpanRegistre = new ClickableSpan() {
      @Override
      public void onClick(@NonNull View view) {
          Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
          startActivity(intent);
      }
    };

    spannableStringRegistre.setSpan(
        clickableSpanRegistre,
        startIndexRegistre,
        endIndexRegistre,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    );

    binding.NotSignedUp.setMovementMethod(LinkMovementMethod.getInstance());

    /* Initializations */
    initWidgetListeners();
  }

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        /* Get the values of the widgets */
        boolean correct = true;

        if(correct) {
          Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
          startActivity(intent);
          finish();
        }
      }
    });
  }
}