package edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;

import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ClientPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ProductPO;
import edu.ub.pis2425.cleanarchitectureexample.presentation.viewmodel.livedata.StateLiveData;

public class ViewProductDetailsViewModel extends AndroidViewModel {
  /* Data */
  private Integer quantity;
  private ProductPO product;
  private ClientPO client;
  /* LiveData */
  private final MutableLiveData<ProductPO> productState;
  private final MutableLiveData<Integer> quantityState;
  private final StateLiveData<Void> buyState;

  /* Constructor */
  public ViewProductDetailsViewModel(Application application) {
    super(application);
    /* Attribute init */
    quantity = 1;
    SharedPreferences preferences = application
        .getSharedPreferences("LOGIN", MODE_PRIVATE);
    String json = preferences.getString("CLIENT_MODEL", null);
    client = new Gson().fromJson(json, ClientPO.class);

    /* LiveData init */
    productState = new MutableLiveData<>();
    quantityState = new MutableLiveData<>(quantity);
    buyState = new StateLiveData<>();
  }

  public void setProduct(ProductPO product) {
    this.product = product;
    productState.postValue(this.product);
  }

  /**
   * Returns the product model
   * @return the product model
   */
  public LiveData<ProductPO> getProductState() { return productState ; }

  /**
   * Get the quantity state live data.
   * @return LiveData<Integer>
   */
  public LiveData<Integer> getQuantityState() {
    return quantityState;
  }

  /**
   * Get the buy state live data.
   * @return
   */
  public StateLiveData<Void> getBuyState() {
    return buyState;
  }

  public void increaseQuantity() {
    quantity += 1;
    quantityState.postValue(quantity);
  }

  public void decreaseQuantity() {
    quantity -= 1;
    quantityState.postValue(quantity);
  }

  public void buyProduct() {
    if (quantity < 1) {
      buyState.postError(new Throwable("Cannot buy less than 1 units"));
    } else if (quantity >= 10) {
      buyState.postError(new Throwable("Cannot buy more than 10 units"));
    } else {
      buyState.postComplete();
    }
  }
}
