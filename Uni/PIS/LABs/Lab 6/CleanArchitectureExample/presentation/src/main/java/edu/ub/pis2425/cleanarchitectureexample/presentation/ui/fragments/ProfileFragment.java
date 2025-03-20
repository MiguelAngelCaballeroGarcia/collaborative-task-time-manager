package edu.ub.pis2425.cleanarchitectureexample.presentation.ui.fragments;

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

import com.squareup.picasso.Picasso;

import edu.ub.pis2425.cleanarchitectureexample.R;
import edu.ub.pis2425.cleanarchitectureexample.databinding.FragmentProfileBinding;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ClientPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.ProfileViewModel;


public class ProfileFragment extends Fragment {

  private ProfileViewModel profileViewModel;

  /* View binding & navigation */
  private NavController navController;
  private FragmentProfileBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    navController = Navigation.findNavController(view);

    /* Initializations */
    initWidgetListeners();
    initViewModels();
  }

  private void initWidgetListeners() {
    binding.btnLogout.setOnClickListener(v -> {
      navController.navigate(R.id.action_profileFragment_to_logInFragment);
    });
  }

  private void initViewModels() {
    profileViewModel = new ViewModelProvider(
        this
    ).get(ProfileViewModel.class);

    initObservers();
  }

  private void initObservers() {
    profileViewModel.getClientState().observe(getViewLifecycleOwner(), clientState -> {
      switch (clientState.getStatus()) {
        case SUCCESS:
          ClientPO clientPO = clientState.getData();
          Picasso.get().load(clientPO.getPhotoUrl()).into(binding.ivProfileImage);
          binding.tvProfileId.setText(clientPO.getId().toString());
          binding.tvProfileEmail.setText(clientPO.getEmail());
          break;
        case ERROR:
          Toast.makeText(getContext(), "Error loading profile info!", Toast.LENGTH_SHORT).show();
          break;
      }
    });
  }
}