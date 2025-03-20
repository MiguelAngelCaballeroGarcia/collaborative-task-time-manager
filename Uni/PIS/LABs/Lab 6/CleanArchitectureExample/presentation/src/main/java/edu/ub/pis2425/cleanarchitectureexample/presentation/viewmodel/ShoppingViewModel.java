package edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetAllProductsUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.GetProductsByNameUseCase;
import edu.ub.pis2425.cleanarchitectureexample.features.usecases.RemoveProductUseCase;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ProductPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.mappers.DomainToPOMapper;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.livedata.StateLiveData;

public class ShoppingViewModel extends ViewModel {
  /* Use cases */
  GetAllProductsUseCase getAllProductsUseCase;
  GetProductsByNameUseCase getProductsByNameUseCase;
  RemoveProductUseCase removeProductUseCase;
  /* Data */
  private final List<ProductPO> products;
  /* LiveData */
  private final StateLiveData<List<ProductPO>> productsState;  // products' list
  private final StateLiveData<Integer> removedProductState;

  /* Constructor */
  public ShoppingViewModel(
      GetAllProductsUseCase getAllProductsUseCase,
      GetProductsByNameUseCase getProductsByNameUseCase,
      RemoveProductUseCase removeProductUseCase
  ) {
    super();
    this.getAllProductsUseCase = getAllProductsUseCase;
    this.getProductsByNameUseCase = getProductsByNameUseCase;
    this.removeProductUseCase = removeProductUseCase;

    products = new ArrayList<>();
    productsState = new StateLiveData<>();
    removedProductState = new StateLiveData<>();
  }

  /**
   * Returns the state of the products being fetched
   * @return the state of the products being fetched
   */
  public StateLiveData<List<ProductPO>> getProductsState() {
    return productsState;
  }

  /**
   * Returns the state of the product being hidden
   * @return
   */
  public StateLiveData<Integer> getRemovedProductState() {
    return removedProductState;
  }

  /**
   * Fetches the products from a data store
   */
  public void fetchProductsCatalog() {
    getAllProductsUseCase.execute(new GetAllProductsUseCase.OnGetProductsListener() {
      @Override
      public void onSuccess(List<Product> gottenProducts) {
        List<ProductPO> gottenProductsPOs = DomainToPOMapper.mapList(gottenProducts, ProductPO.class);
        products.clear();
        products.addAll(gottenProductsPOs);
        productsState.postSuccess(Collections.unmodifiableList(products));
      }
      @Override
      public void onError(Throwable error) {
        productsState.postError(error);
      }
    });

  }

  /**
   * Fetches the products using the use case
   */
  public void fetchProductsByName(String name) {
    getProductsByNameUseCase.execute(name, new GetProductsByNameUseCase.OnGetProductsByNameListener() {
      @Override
      public void onSuccess(List<Product> gottenProducts) {
        List<ProductPO> gottenProductsPOs = DomainToPOMapper.mapList(gottenProducts, ProductPO.class);
        products.clear();
        products.addAll(gottenProductsPOs);
        productsState.postSuccess(Collections.unmodifiableList(products));
      }
      @Override
      public void onError(Throwable error) {
        productsState.postError(error);
      }
    });
  }

  /**
   * Remove a product in the products' list
   * @param product the product to be hidden
   */
  public void removeProduct(ProductPO product) {
    removeProductUseCase.execute(product.getId().toString(), new RemoveProductUseCase.OnRemoveProductListener() {
      @Override
      public void onSuccess() {
        Integer position = products.indexOf(product);
        products.remove(product);
        removedProductState.postSuccess(position);
      }

      @Override
      public void onError(Throwable error) {
        removedProductState.postError(error);
      }
    });
  }
}
