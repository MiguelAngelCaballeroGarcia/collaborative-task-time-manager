package edu.ub.pis2425.cleanarchitectureexample.presentation.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import edu.ub.pis2425.cleanarchitectureexample.R;
import edu.ub.pis2425.cleanarchitectureexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
  /* Attributes */
  private NavController navController;
  AppBarConfiguration appBarConfiguration;
  private ActivityMainBinding binding;

  /**
   * Called when the activity is being created.
   * @param savedInstanceState The saved instance state.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setSupportActionBar(binding.toolbar);

    /* Initializations */
    initNavigation();
  }

  /**
   * Initialize the navigation.
   */
  private void initNavigation() {
    /* Set up the navigation controller */
    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment_main);
    assert navHostFragment != null;
    navController = navHostFragment.getNavController();

    /* Set up the bottom navigation, indicating the fragments that will take part on it. */
    appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.shoppingFragment,
        R.id.profileFragment
    ).build();

    /* Set up navigation with both the action bar and the bottom navigation view */
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

    /*
      Detect when the navigation destination changes.
     */
    navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
      // Set the visibility of the bottom navigation view and the upper toolbar depending
      // on the navigation destination
      setBottomNavigationViewVisibility(destination);
      setActionBarVisibility(destination);
    });
  }

  /**
   * Set the visibility of the bottom navigation view depending on the navigation destination.
   * @param destination The navigation destination.
   */
  private void setBottomNavigationViewVisibility(NavDestination destination) {
    /* If the destination is the log in or sign up fragment, hide the bottom navigation view */
    if (destination.getId() == R.id.logInFragment || destination.getId() == R.id.signUpFragment) {
      binding.bottomNavigationView.setVisibility(View.GONE);
    } else {
      binding.bottomNavigationView.setVisibility(View.VISIBLE);
    }
  }

  /**
   * Set the visibility of the upper toolbar depending on the navigation destination.
   * @param destination The navigation destination.
   */
  private void setActionBarVisibility(NavDestination destination) {
    /* If the destination is the log in or sign up fragment, hide the bottom navigation view */
    if (destination.getId() == R.id.logInFragment) {
      binding.toolbar.setVisibility(View.GONE);
    } else {
      binding.toolbar.setVisibility(View.VISIBLE);
    }
  }

  /**
   * Handle the navigating up action (left-pointing arrow in the action bar).
   * @return
   */
  @Override
  public boolean onSupportNavigateUp() {
    /* Enable the up navigation */
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }
}