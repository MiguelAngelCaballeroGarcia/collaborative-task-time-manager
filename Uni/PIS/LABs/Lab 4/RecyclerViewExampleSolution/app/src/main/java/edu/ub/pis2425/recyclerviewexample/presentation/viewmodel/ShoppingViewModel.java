package edu.ub.pis2425.recyclerviewexample.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ub.pis2425.recyclerviewexample.domain.Product;
import edu.ub.pis2425.recyclerviewexample.data.services.ProductStoreService;
import edu.ub.pis2425.recyclerviewexample.utils.livedata.StateLiveData;

public class ShoppingViewModel extends ViewModel {
  /* Attributes */
  ProductStoreService productStoreService;
  private final List<Product> products;
  /* LiveData */
  private final StateLiveData<List<Product>> productsState;  // products' list
  private final MutableLiveData<Integer> removedProductState;

  /* Constructor */
  public ShoppingViewModel() {
    super();
    productStoreService = new ProductStoreService();
    products = new ArrayList<>();
    productsState = new StateLiveData<>();
    removedProductState = new MutableLiveData<>();
  }

  /**
   * Returns the state of the products being fetched
   * @return the state of the products being fetched
   */
  public StateLiveData<List<Product>> getProductsState() {
    return productsState;
  }

  /**
   * Returns the state of the product being hidden
   * @return
   */
  public LiveData<Integer> getRemovedProductState() {
    return removedProductState;
  }

  /**
   * Fetches the products from a data store
   */
  public void fetchProductsCatalog() {
    productStoreService.getAll(new ProductStoreService.OnFetchProductsListener() {
      @Override
      public void onFetchProducts(List<Product> gottenProducts) {
        products.clear();
        products.addAll(gottenProducts);
        productsState.postSuccess(Collections.unmodifiableList(products));
      }
    });

  }

  /**
   * Fetches the products using the use case
   */
  public void fetchProductsByName(String name) {
    productStoreService.getByName(name, new ProductStoreService.OnFetchProductsListener() {
      @Override
      public void onFetchProducts(List<Product> gottenProducts) {
        products.clear();
        products.addAll(gottenProducts);
        productsState.postSuccess(Collections.unmodifiableList(products));
      }
    });
  }

  /**
   * Remove a product in the products' list
   * @param product the product to be hidden
   */
  public void removeProduct(Product product) {
    /* EXERCICI 2: NO TOCAR */
    int position = products.indexOf(product);
    products.remove(product);
    removedProductState.postValue(position);
  }
}
