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

import edu.ub.pis2425.cleanarchitectureexample.MyApplication;
import edu.ub.pis2425.cleanarchitectureexample.databinding.FragmentViewProductDetailsBinding;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ProductPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.ViewProductDetailsViewModel;

public class ViewProductDetailsFragment extends Fragment {

  private ViewProductDetailsViewModel viewProductDetailsViewModel;
  private NavController navController;
  private FragmentViewProductDetailsBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentViewProductDetailsBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    navController = Navigation.findNavController(view);

    /* Initializations */
    initWidgetListeners();
    initViewModel();

    /* Get product model from arguments */
    assert getArguments() != null;
    ProductPO productPO = getArguments().getParcelable("PRODUCT");
    assert productPO != null;
    viewProductDetailsViewModel.setProduct(productPO);
  }

  /**
   * Initialize the listeners of the widgets
   */
  private void initWidgetListeners() {
    // Set listener of the "+" button
    binding.ibtnIncreaseItemQuantity.setOnClickListener(v ->
        viewProductDetailsViewModel.increaseQuantity()
    );

    // Set listener of the "-" button
    binding.ibtnDecreaseItemQuantity.setOnClickListener(v ->
        viewProductDetailsViewModel.decreaseQuantity()
    );

    // Set listener of the "Buy" button
    binding.btnBuy.setEnabled(true);
    binding.btnBuy.setOnClickListener(v -> {
      binding.btnBuy.setEnabled(false);
      viewProductDetailsViewModel.buyProduct();
    });
  }

  /**
   * Initialize the view model
   */
  private void initViewModel() {
    /* Init viewmodel */
    viewProductDetailsViewModel = new ViewModelProvider(
        this,
        ((MyApplication) requireActivity().getApplication()).getViewModelFactory()
    ).get(ViewProductDetailsViewModel.class);

    /* Initialize the observers */
    initObservers();
  }

  /**
   * Initialize the observers of the view model
   */
  private void initObservers() {
    /* Observe changes in the product state */
    viewProductDetailsViewModel.getProductState().observe(getViewLifecycleOwner(), productModel -> {
      binding.tvDialogProductName.setText(productModel.getName());
      String priceText = productModel.getPrice();
      binding.tvDialogProductPrice.setText(priceText);
      binding.tvDialogProductDescription.setText(productModel.getDescription());
      Picasso.get().load(productModel.getImageUrl()).into(binding.ivDialogProductImage);
    });

    /* Observe changes in the quantity */
    viewProductDetailsViewModel.getQuantityState().observe(getViewLifecycleOwner(), quantity ->
      binding.tvDialogProductQuantity.setText(quantity.toString())
    );

    /* Observe changes in the added to cart state */
    viewProductDetailsViewModel.getBuyState().observe(getViewLifecycleOwner(), state -> {
      switch (state.getStatus()) {
        case LOADING:
          binding.btnBuy.setEnabled(false);
          break;
        case COMPLETE:
          binding.btnBuy.setEnabled(true);
          Toast.makeText(getActivity(), "Product added to cart!", Toast.LENGTH_SHORT).show();
          navController.navigateUp();
          break;
        case ERROR:
          binding.btnBuy.setEnabled(true);
          Toast.makeText(getActivity(), "Error adding product to cart", Toast.LENGTH_SHORT).show();
          navController.navigateUp();
          break;
        default:
          break;
      }
    });
  }
}