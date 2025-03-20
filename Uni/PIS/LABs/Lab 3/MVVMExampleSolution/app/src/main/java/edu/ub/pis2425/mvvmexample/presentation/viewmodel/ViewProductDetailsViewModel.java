package edu.ub.pis2425.mvvmexample.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.mvvmexample.utils.livedata.StateLiveData;

public class ViewProductDetailsViewModel extends ViewModel {
  /* Atributtes */
  private Integer quantity;
  /* LiveData */
  private final MutableLiveData<Integer> quantityState;
  // EXERICI 3:
  private final StateLiveData<Void> buyState;
  // ... (extra attributes/mutablelivedata/statelivedata?) ...

  /* Constructor */
  public ViewProductDetailsViewModel() {
    super();
    /* Attribute init */
    quantity = 1;
    /* LiveData init */
    quantityState = new MutableLiveData<>(quantity);
    // EXERCICI 3
    buyState = new StateLiveData<>();
    // ... (other attribute/mutablelivedata/statelivedata initalizations?) ...
  }

  /**
   * Get the quantity state live data.
   * @return LiveData<Integer>
   */
  public LiveData<Integer> getQuantityState() {
    return quantityState;
  }

  public void increaseQuantity() {
    /* EXERCICI 2 */
    quantity += 1;
    quantityState.postValue(quantity);
    // ...
  }

  public void decreaseQuantity() {
    /* EXERCICI 2 */
    // ...
    quantity -= 1;
    quantityState.postValue(quantity);
    // ...
  }

  public void buyProduct() {
    /* EXERCICI 3 */
    if (quantity < 1) {
      buyState.postError(new Throwable("Cannot buy less than 1 units"));
    } else if (quantity >= 10) {
      buyState.postError(new Throwable("Cannot buy more than 10 units"));
    } else {
      buyState.postSuccess(null);
    }
    // ...
  }

  // EXERCICI 3
  public StateLiveData<Void> getBuyState() {
    return buyState;
  }
  // ...
}
