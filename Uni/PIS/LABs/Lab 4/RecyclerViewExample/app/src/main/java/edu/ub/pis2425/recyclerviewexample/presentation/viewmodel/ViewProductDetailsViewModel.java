package edu.ub.pis2425.recyclerviewexample.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.ub.pis2425.recyclerviewexample.utils.livedata.StateLiveData;

public class ViewProductDetailsViewModel extends ViewModel {
  /* Atributtes */
  private Integer quantity;
  /* LiveData */
  private final MutableLiveData<Integer> quantityState;
  private final StateLiveData<Void> buyState;

  /* Constructor */
  public ViewProductDetailsViewModel() {
    super();
    /* Attribute init */
    quantity = 1;
    /* LiveData init */
    quantityState = new MutableLiveData<>(quantity);
    buyState = new StateLiveData<>();
  }

  /**
   * Get the quantity state live data.
   * @return LiveData<Integer>
   */
  public LiveData<Integer> getQuantityState() {
    return quantityState;
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
      buyState.postSuccess(null);
    }
  }

  public StateLiveData<Void> getBuyState() {
    return buyState;
  }
}
