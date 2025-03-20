package edu.ub.pis2425.cleanarchitectureexample.presentation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.ub.pis2425.cleanarchitectureexample.domain.entities.Product;
import edu.ub.pis2425.cleanarchitectureexample.R;
import edu.ub.pis2425.cleanarchitectureexample.presentation.pos.ProductPO;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {
  /* Attributes */

  /* Product list to show in the recycler view */
  private List<ProductPO> productList;
  /* Listener to callback the activity */
  private final OnProductClickListener onProductClickListener;
  private final OnProductRemoveListener onProductRemoveListener;

  /**
   * Interface to callback the activity when a product is clicked.
   */
  public interface OnProductClickListener {
    void onProductClick(ProductPO product);
  }

  public interface OnProductRemoveListener {
    void onProductRemove(ProductPO product);
  }

  /**
   * Constructor
   * @param onProductClickListener: Listener to callback the activity.
   */
  public ProductRecyclerViewAdapter(
      OnProductClickListener onProductClickListener,
      OnProductRemoveListener onProductRemoveListener
  ) {
    super();
    this.onProductClickListener = onProductClickListener;
    this.onProductRemoveListener = onProductRemoveListener;
  }

  /**
   * Set the reference to the data displayed in the recycler view.
   * @param productModelList: The list of products to be displayed.
   */
  @SuppressLint("NotifyDataSetChanged")
  public void setProductsData(List<ProductPO> productModelList) {
    this.productList = productModelList; // Note that this is a reference, not a copy. It is
                                    // instead modified by the ViewModel directly
    notifyDataSetChanged(); // Reflect the changes in the UI
  }

  /**
   * Remove the item at position
   * @param position
   */
  public void removeProductAt(Integer position) {
    notifyItemRemoved(position);
  }

  /**
   * Create a ViewHolder object for each data element.
   * @param parent: The parent ViewGroup. In this case, the RecyclerView.
   * @param viewType: The type of the view. In this case, it is not used.
   */
  @NonNull
  @Override
  public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    /* Create a view holder's view inflating layout */
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_rv_product, parent, false);

    /* Create view holder with injected view and listeners */
    return new ProductViewHolder(
        view,
        position -> onProductClickListener.onProductClick(productList.get(position)),
        position -> onProductRemoveListener.onProductRemove(productList.get(position))
    );
  }

  /**
   * Bind the actual data of the Product to the ViewHolder object created in
   * onCreateViewHolder.
   * @param holder The ViewHolder which should be updated to represent the contents of the
   *        item at the given position in the data set.
   * @param position The position of the item within the adapter's data set.
   */
  @Override
  public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    /* Index the product using position */
    ProductPO p = productList.get(position);

    /* Get data from the product */
    String name = p.getName();
    String price = p.getPrice();
    String imageUrl = p.getImageUrl();

    /* Set product data to view holder */
    holder.tvProductName.setText(name);
    holder.tvProductPrice.setText(price);
    Picasso.get().load(imageUrl).into(holder.ivProductImage);
  }

  /**
   * Get the number of data elements. Used internally by the RecyclerView.
   * @return The number of data elements.
   */
  @Override
  public int getItemCount() {
    return (productList == null) ? 0 : productList.size();
  }

  /**
   * ViewHolder class definition.
   */
  public static class ProductViewHolder extends RecyclerView.ViewHolder {
    private final ImageView ivProductImage;
    private final TextView tvProductName;
    private final TextView tvProductPrice;
    private final ImageView ivRemoveItem;

    /**
     * Interface to callback the adapter when a product
     * at some position is clicked.
     */
    public interface OnPositionClickListener {
      void onPositionClick(int position);
    }

    /**
     * Interface to callback the adapter when a product's remove is clicked.
     */
    public interface OnPositionRemoveListener {
      void onPositionRemove(int position);
    }

    /**
     * Constructor
     * @param itemView: The view of the ViewHolder.
     * @param onPositionClickListener: Listener to callback the adapter.
     */
    public ProductViewHolder(
        @NonNull View itemView,
        OnPositionClickListener onPositionClickListener,
        OnPositionRemoveListener onPositionRemoveListener
    ) {
      super(itemView);

      ivProductImage = itemView.findViewById(R.id.ivProductImage);
      tvProductName = itemView.findViewById(R.id.tvProductName);
      tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
      ivRemoveItem = itemView.findViewById(R.id.ivRemoveItem);

      itemView.setOnClickListener(v ->
          onPositionClickListener.onPositionClick(getAdapterPosition())
      );

      ivRemoveItem.setOnClickListener(v ->
          onPositionRemoveListener.onPositionRemove(getAdapterPosition())
      );
    }
  }
}
