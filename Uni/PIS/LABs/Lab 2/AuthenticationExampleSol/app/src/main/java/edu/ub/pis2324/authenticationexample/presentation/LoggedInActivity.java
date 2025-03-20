package edu.ub.pis2324.authenticationexample.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.squareup.picasso.Picasso;

import edu.ub.pis2324.authenticationexample.databinding.ActivityLoggedInBinding;
import edu.ub.pis2324.authenticationexample.domain.Client;

public class LoggedInActivity extends AppCompatActivity {

  private ActivityLoggedInBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoggedInBinding
        .inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    String clientId = getIntent().getStringExtra("CLIENT_ID");
    String clientPhotoUrl = getIntent().getStringExtra("CLIENT_PHOTO_URL");

    binding.tvClientId.setText(clientId);
    Picasso.get().load(clientPhotoUrl).into(binding.ivClientPhoto);

    initWidgetListeners();
  }

  private void initWidgetListeners() {
    binding.btnLogOut.setOnClickListener(ignoredView -> {
      Intent intent = new Intent(this, LogInActivity.class);
      startActivity(intent);
      finish();
    });
  }
}