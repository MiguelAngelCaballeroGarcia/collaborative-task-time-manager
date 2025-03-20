package edu.ub.pis2425.mvvmexample.presentation.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.Locale;

import edu.ub.pis2425.mvvmexample.databinding.ActivityViewProductDetailsBinding;
import edu.ub.pis2425.mvvmexample.domain.Product;
import edu.ub.pis2425.mvvmexample.presentation.viewmodel.ViewProductDetailsViewModel;


public class ViewProductDetailsActivity extends AppCompatActivity {
  /* Attributes */

  /* ViewModel */
  private ViewProductDetailsViewModel viewProductDetailsViewModel; // (Per l'EXERCICI 2)
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

    /* EXERCICI 1 */
    // ...

    initWidgetListeners();
  }

  /**
   * Initialize the listeners of the widgets
   */
  private void initWidgetListeners() {
    /* EXERCICI 1 */
    // ...
  }

  /**
   * Initialize the view model and its observers
   */
  private void initViewModel() {
    /* EXERCICI 2 */
    // ...

    initObservers();
  }

  /**
   * Initialize the observers of the view model
   */
  private void initObservers() {
    /* EXERCICI 2 */
    // ...

    /* EXERCICI 3 */
    // ...
  }
}