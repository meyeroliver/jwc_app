package za.grounded.jwc_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> productList = new ArrayList<>();
    private MutableLiveData<String> onClickedProduct = new MutableLiveData<>();


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.product, parent, false);

        return new ProductHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = this.productList.get(position);
        holder.title.setText(product.getItem());
        holder.price.setText("R " + product.getPrice());
        holder.setProduct(product);
    }

    @Override
    public int getItemCount() {
        return this.productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LiveData<String> getOnClickedProduct() {
        return onClickedProduct;
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView price;
        private MaterialButton addToCart;
        private Product product;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            addToCart = itemView.findViewById(R.id.add_to_cart);

            addToCart.setOnClickListener(v -> {
                onClickedProduct.setValue(product.get_id());
            });
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }
}
