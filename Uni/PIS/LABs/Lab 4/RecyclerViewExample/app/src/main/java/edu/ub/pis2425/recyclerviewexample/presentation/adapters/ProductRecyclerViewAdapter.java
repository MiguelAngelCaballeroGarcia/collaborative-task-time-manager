package edu.ub.pis2425.recyclerviewexample.presentation.adapters;

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

import edu.ub.pis2425.recyclerviewexample.R;
import edu.ub.pis2425.recyclerviewexample.domain.Product;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {
  /* Attributes */

  /* Product list to show in the recycler view */
  private List<Product> productList;
  /* Listener to callback the activity */
  private final OnProductClickListener onProductClickListener;
  /* EXERCICI 1 */
  // ...

  /**
   * Interface to callback the activity when a product is clicked.
   */
  public interface OnProductClickListener {
    void onProductClick(Product product);
  }

  /* EXERCICI 1 */
  // ...

  /**
   * Constructor
   * @param onProductClickListener: Listener to callback the activity.
   */
  public ProductRecyclerViewAdapter(
      OnProductClickListener onProductClickListener
      /* EXERCICI 1 */
      // ...
  ) {
    super();
    this.onProductClickListener = onProductClickListener;
    /* EXERCICI 1 */
    // ...
  }

  /**
   * Set the reference to the data displayed in the recycler view.
   * @param productModelList: The list of products to be displayed.
   */
  @SuppressLint("NotifyDataSetChanged")
  public void setProductsData(List<Product> productModelList) {
    this.productList = productModelList; // Note that this is a reference, not a copy. It is
                                    // instead modified by the ViewModel directly
    notifyDataSetChanged(); // Reflect the changes in the UI
  }

  /* EXERCICI 1: JA FET, NO CAL MODIFICAR */
  /**
   * Removes the item at position
   * @param position
   */
  public void removePositionAt(int position) {
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
        position -> onProductClickListener.onProductClick(productList.get(position))
        /* EXERCICI 1 */
        // ...
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
    Product p = productList.get(position);

    /* Get data from the product */
    String name = p.getName();
    String price = p.getPrice().toString();
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
    /* EXERCICI 1 */
    // ...

    /**
     * Interface to callback the adapter when a product
     * at some position is clicked.
     */
    public interface OnPositionClickListener {
      void onPositionClick(int position);
    }

    /* EXERCICI 1 */
    // ...

    /**
     * Constructor
     * @param itemView: The view of the ViewHolder.
     * @param onPositionClickListener: Listener to callback the adapter.
     */
    public ProductViewHolder(
        @NonNull View itemView,
        OnPositionClickListener onPositionClickListener
        /* EXERCICI 1 */
        // ...
    ) {
      super(itemView);

      ivProductImage = itemView.findViewById(R.id.ivProductImage);
      tvProductName = itemView.findViewById(R.id.tvProductName);
      tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
      /* EXERCICI 1 */
      // ...

      itemView.setOnClickListener(v ->
          onPositionClickListener.onPositionClick(getAdapterPosition())
      );

      /* EXERCICI 1 */
      // ...
    }
  }
}
