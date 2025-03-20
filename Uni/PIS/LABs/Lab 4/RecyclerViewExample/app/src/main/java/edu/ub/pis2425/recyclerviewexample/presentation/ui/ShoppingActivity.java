package edu.ub.pis2425.recyclerviewexample.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.ub.pis2425.recyclerviewexample.databinding.ActivityShoppingBinding;
import edu.ub.pis2425.recyclerviewexample.domain.Product;
import edu.ub.pis2425.recyclerviewexample.presentation.adapters.ProductRecyclerViewAdapter;
import edu.ub.pis2425.recyclerviewexample.presentation.viewmodel.ShoppingViewModel;

public class ShoppingActivity extends AppCompatActivity {
  /* Constants */
  private static final String RECYCLER_VIEW_STATE = "recycler_view_state";

  /* Attributes */

  /* This activity's view model */
  private ShoppingViewModel shoppingViewModel;
  /* View binding */
  private ActivityShoppingBinding binding;
  /* Client id */
  private String clientId;
  /* Adapter for the recycler view of products */
  private ProductRecyclerViewAdapter rvProductsAdapter;
  /* LayoutManager for the recycler view of products */
  private RecyclerView.LayoutManager rvLayoutManager;
  private Parcelable rvStateParcelable; // to save state of the rv's layout manager (scroll)

  /**
   * Called when the activity is being created.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /* Set view binding */
    binding = ActivityShoppingBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    /* Get client id from intent */
    clientId = getIntent().getStringExtra("CLIENT_ID");

    /* Initializations */
    initWidgetListeners();
    initRecyclerView();
    initViewModel();
  }

  /**
   * Lifecycle method called when the activity is being resumed.
   */
  protected void onResume() {
    super.onResume();
    shoppingViewModel.fetchProductsCatalog();
  }

  /**
   * Lifecycle method called when the activity is being paused.
   * @param state the bundle to save the state of the activity
   */
  protected void onSaveInstanceState(@NonNull Bundle state) {
    super.onSaveInstanceState(state);
    rvStateParcelable = rvLayoutManager.onSaveInstanceState();
    state.putParcelable(RECYCLER_VIEW_STATE, rvStateParcelable);
  }

  /**
   * Lifecycle method called when the activity is being restored.
   * @param state the bundle to restore the state of the activity
   */
  protected void onRestoreInstanceState(@NonNull Bundle state) {
    super.onRestoreInstanceState(state);
    rvStateParcelable = state.getParcelable(RECYCLER_VIEW_STATE);
  }

  /**
   * Initialize the listeners of the widgets.
   */
  private void initWidgetListeners() {
    /* Search view */
    binding.svProducts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String queryText) {
        shoppingViewModel.fetchProductsByName(queryText);
        return true;
      }
      @Override
      public boolean onQueryTextChange(String queryText) {
        /*
         * "Hack" per suplir OnCloseListener. Més info:
         *  See: https://stackoverflow.com/questions/9327826/
         *   searchviews-oncloselistener-doesnt-work
         * Altrament fariem simplement: return false.
         */
        if (!queryText.isEmpty()) return false;
        shoppingViewModel.fetchProductsCatalog();
        return true;
      }
    });
  }

  /**
   * Initialize the recycler view.
   */
  private void initRecyclerView() {
    rvLayoutManager = new LinearLayoutManager(this);
    rvProductsAdapter = new ProductRecyclerViewAdapter(
        product -> startViewProductDetailsActivity(product)
        /* EXERCICI 1 */
        // ...
    );

    binding.rvProducts.setLayoutManager(rvLayoutManager);
    binding.rvProducts.setAdapter(rvProductsAdapter);
  }

  /**
   * Initialize the view model.
   */
  private void initViewModel() {
    /*
     * Get the view model from the ViewModelProvider. If we where to use "new ShoppingViewModel()"
     * the view model would not be aware of the activity's lifecycle. So every time the activity
     * is destroyed and recreated, the view model would be a new one, and we would lose the
     * data that we had in the previous one, when to keep the data is its very own purpose.
     */
    shoppingViewModel = new ViewModelProvider(this).get(ShoppingViewModel.class);
    initObservers();
  }

  /**
   * Initialize the observers of the view model.
   */
  private void initObservers() {
    /* Observe the state of the products' catalog */
    shoppingViewModel.getProductsState().observe(this, state -> {
      switch (state.getStatus()) {
        case SUCCESS:
          assert state.getData() != null;
          showNoProductsAvailable(state.getData().isEmpty());
          rvProductsAdapter.setProductsData(state.getData());
          break;
        case ERROR:
          showNoProductsAvailable(true);
          break;
        default:
          break;
      }
    });

    /* EXERCICI 1 */
    // ...
  }

  private void showNoProductsAvailable(boolean mustShow) {
    binding.clNoProductsAvailable.setVisibility(
        mustShow ? android.view.View.VISIBLE : android.view.View.GONE
    );
  }

  /**
   * Starts the ViewProductDetailsActivity.
   * @param product the product to be shown
   */
  private void startViewProductDetailsActivity(Product product) {
    Intent intent = new Intent(this, ViewProductDetailsActivity.class);
    intent.putExtra("CLIENT_ID", clientId);
    intent.putExtra("PRODUCT", product); // Product class implements Parcelable
    startActivity(intent);
  }
}