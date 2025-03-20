package edu.ub.pis2425.recyclerviewexample.presentation.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.Locale;

import edu.ub.pis2425.recyclerviewexample.databinding.ActivityViewProductDetailsBinding;
import edu.ub.pis2425.recyclerviewexample.domain.Product;
import edu.ub.pis2425.recyclerviewexample.presentation.viewmodel.ViewProductDetailsViewModel;


public class ViewProductDetailsActivity extends AppCompatActivity {
  /* Attributes */

  /* ViewModel */
  private ViewProductDetailsViewModel viewProductDetailsViewModel;
  /* View binding */
  private ActivityViewProductDetailsBinding binding;

  /**
   * Called when the activity is being created.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* Set view binding */
    binding = ActivityViewProductDetailsBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    Product product = (Product) getIntent()
        .getParcelableExtra("PRODUCT");

    /* Initializations */
    assert product != null;
    initWidgets(product);
    initViewModel();
  }

  /**
   * Initialize the values of the widgets and their listeners
   * @param product The product model whose details are being shown.
   */
  private void initWidgets(Product product) {
    // Carrega l'imatge d'una URL a un ImageView amb la llibereria Picasso
    RequestCreator rc = Picasso.get().load(product.getImageUrl());
    rc.into(binding.ivDetailsProductImage);

    binding.tvDetailsProductName.setText(product.getName());
    binding.tvDetailsProductPrice.setText(product.getPrice().toString());
    binding.tvDialogProductDescription.setText(product.getDescription());

    initWidgetListeners();
  }

  /**
   * Initialize the listeners of the widgets
   */
  private void initWidgetListeners() {
    binding.ibtnIncreaseItemQuantity.setOnClickListener(view -> viewProductDetailsViewModel.increaseQuantity());
    binding.ibtnDecreaseItemQuantity.setOnClickListener(view -> viewProductDetailsViewModel.decreaseQuantity());
    binding.btnBuy.setOnClickListener(view -> viewProductDetailsViewModel.buyProduct());
  }

  /**
   * Initialize the view model and its observers
   */
  private void initViewModel() {
    viewProductDetailsViewModel = new ViewModelProvider(
    this
    ).get(ViewProductDetailsViewModel.class);

    initObservers();
  }

  /**
   * Initialize the observers of the view model
   */
  private void initObservers() {
    viewProductDetailsViewModel.getQuantityState().observe(
        this,
        quantity -> binding.tvProductQuantity.setText(String.format(Locale.getDefault(), "%d", quantity))
    );

    viewProductDetailsViewModel.getBuyState().observe(
        this,
        buyState -> {
          switch (buyState.getStatus()) {
            case SUCCESS:
              finish();
              break;
            case ERROR:
              Toast.makeText(this, buyState.getError().getMessage(), Toast.LENGTH_SHORT).show();
              break;
            default:
              // Do nothing
              break;
          }
        }
    );
  }
}