package edu.ub.pis2324.authenticationexample.presentation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemeUtils;
import androidx.core.content.ContextCompat;

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
  @SuppressLint("ResourceAsColor")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /* Set view binding */
    binding = ActivityLogInBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    Log.d("LogInActivity", "El meu missatge de log.");

    /* Subrayar el "Regístrate" del TextView NotSignedUp */
    SpannableString spannableStringRegistre = new SpannableString(getString(R.string.not_signed_up));
    UnderlineSpan underlineSpanRegistre = new UnderlineSpan();

    int startIndexRegistre = getString(R.string.not_signed_up).indexOf("Regístrate");
    int endIndexRegistre = startIndexRegistre + "Regístrate".length();

    spannableStringRegistre.setSpan(
        underlineSpanRegistre,
        startIndexRegistre,
        endIndexRegistre,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    /* Hacer clicable el "Regístrate" del TextView NotSignedUp */
    ClickableSpan clickableSpanRegistre = new ClickableSpan() {
      @Override
      public void onClick(@NonNull View view) {
          Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
          startActivity(intent);
      }

      @Override
      public void updateDrawState(@NonNull android.text.TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(binding.NotSignedUp.getCurrentTextColor());
        ds.setUnderlineText(true);
        ds.linkColor=Color.TRANSPARENT;
      }
    };

    spannableStringRegistre.setSpan(
        clickableSpanRegistre,
        startIndexRegistre,
        endIndexRegistre,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    );

    binding.NotSignedUp.setText(spannableStringRegistre);
    binding.NotSignedUp.setMovementMethod(LinkMovementMethod.getInstance());
    binding.NotSignedUp.setHighlightColor(Color.TRANSPARENT);

    /* Subrayar el TextView ForgotPassword */
    SpannableString spannableStringFP = new SpannableString(getString(R.string.forgot_password));
    UnderlineSpan underlineSpanFP = new UnderlineSpan();

    int startIndexFP = 0;
    int endIndexFP = getString(R.string.forgot_password).length();

    spannableStringFP.setSpan(
            underlineSpanFP,
            startIndexFP,
            endIndexFP,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    /* Hacer clicable el TextView ForgotPassword */
    ClickableSpan clickableSpanFP = new ClickableSpan() {
      @Override
      public void onClick(@NonNull View view) {
        // RELLENAR, CONTRASEÑA OLVIDADA
      }

      @Override
      public void updateDrawState(@NonNull android.text.TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(binding.ForgotPassword.getCurrentTextColor());
        ds.setUnderlineText(true);
        ds.linkColor=Color.TRANSPARENT;
      }
    };

    spannableStringFP.setSpan(
            clickableSpanFP,
            startIndexFP,
            endIndexFP,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    );

    binding.ForgotPassword.setText(spannableStringFP);
    binding.ForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
    binding.ForgotPassword.setHighlightColor(Color.TRANSPARENT);

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