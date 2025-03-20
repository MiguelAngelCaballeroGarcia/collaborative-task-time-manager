package edu.ub.pis2425.mvvmexample.presentation.adapters;

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

import edu.ub.pis2425.mvvmexample.R;
import edu.ub.pis2425.mvvmexample.domain.Product;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {
  /* Attributes */

  /* Product list to show in the recycler view */
  private List<Product> productList;
  /* Listener to callback the activity */
  private final OnProductClickListener onProductClickListener;
  /* EXERCICI 2 */
  // ...

  /**
   * Interface to callback the activity when a product is clicked.
   */
  public interface OnProductClickListener {
    void onProductClick(Product productModel);
  }

  /* EXERCICI 2 */
  // ...

  /**
   * Constructor
   * @param onProductClickListener: Listener to callback the activity.
   */
  public ProductRecyclerViewAdapter(
      OnProductClickListener onProductClickListener
      /* EXERCICI 2 */
  ) {
    super();
    this.onProductClickListener = onProductClickListener;
    /* EXERCICI 2 */
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

  /* EXERCICI 2 */
  // ...

  /**
   * Create a ViewHolder object for each data element.
   * @param parent: The parent ViewGroup. In this case, the RecyclerView.
   * @param viewType: The type of the view. In this case, it is not used.
   */
  @NonNull
  @Override
  public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    /* Create a ViewHolder object for this data element */
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_rv_product, parent, false);

    /* Return the ViewHolder object */
    return new ProductViewHolder(view,
      position -> onProductClickListener.onProductClick(productList.get(position))
      /* EXERCICI 2 */
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
    /* Get the data from the data element */
    Product p = productList.get(position);
    String name = p.getName();
    String price = p.getPrice().toString();
    String imageUrl = p.getImageUrl();

    /* Set the data to the ViewHolder */
    holder.tvProductName.setText(name);
    holder.tvProductPrice.setText(price);
    Picasso.get().load(imageUrl).into(holder.ivProductImage); // Internet URL image -> ImageView
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
    /* EXERCICI 2 */
    // ...

    /**
     * Interface to callback the adapter when a product
     * at some position is clicked.
     */
    public interface OnItemPositionClickListener {
      void onItemPositionClick(int position);
    }

    /**
     * Interface to callback the adapter when a product's hide icon
     * at some position is clicked.
     */
    public interface OnItemHideIconClickListener {
      void onItemHideIconClick(int position);
    }

    /**
     * Constructor
     * @param itemView: The view of the ViewHolder.
     * @param onItemPositionClickListener: Listener to callback the adapter.
     */
    public ProductViewHolder(
      @NonNull View itemView,
      OnItemPositionClickListener onItemPositionClickListener
      /* EXERCICI 2 */
    ) {
      super(itemView);

      ivProductImage = itemView.findViewById(R.id.ivProductImage);
      tvProductName = itemView.findViewById(R.id.tvProductName);
      tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
      /* EXERCICI 2 */
      // ...

      /*
        Seteja un OnClickListener normal que detecti clic a qualsevol lloc del ViewHolder,
        i faci el callback a través del listener que hem declarat definit aquí, de tipus
        OnItemPositionClickListener i definit anònimament a l'onCreateViewHolder.

        Més detall: Com que només es pot accedir a la posició clicada des del propi ViewHolder,
        però el productList és un atribut de l'adaptador, hem de caçar aquí la posició
        clicada i passar-li a l'adaptador. Després, des de l'adaptador accedir a la
        productList per obtenir l'element a través d'un altre listener que es comunicarà
        amb l'activitat (OnProductClickListener).
      */
      itemView.setOnClickListener(v ->
        onItemPositionClickListener.onItemPositionClick(getAdapterPosition())
      );

      /* EXERCICI 2 */
      // ...
    }
  }
}
