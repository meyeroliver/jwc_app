package za.grounded.jwc_app.adapter;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.CartItem;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    private List<CartItem> cartItemList = new ArrayList<>();
    private MutableLiveData<Boolean> addItem = new MutableLiveData<>();
    private MutableLiveData<Boolean> removeItem = new MutableLiveData<>();

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);

        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CartItem cartItem = this.cartItemList.get(position);
        holder.cartTitle.setText(underlineText(cartItem.getProduct().getItem()));
        holder.cartPrice.setText(underlineText("R " + cartItem.getProduct().getPrice()));
        holder.quantity.setText(cartItem.getQuantity()+"");
        holder.setCartItem(cartItem);
    }

    private SpannableString underlineText(String text) {
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        return content;
    }

    @Override
    public int getItemCount() {
        return this.cartItemList.size();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public LiveData<Boolean> getAddItem() {
        return addItem;
    }

    public void setAddItem(boolean addItem) {
        this.addItem.setValue(addItem);
    }

    public LiveData<Boolean> getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(boolean removeItem) {
        this.removeItem.setValue(removeItem);
    }

    public class CartHolder extends RecyclerView.ViewHolder  {

        private ImageView imageView;
        private TextView cartTitle;
        private TextView cartPrice;
        private TextView quantity;
        private ImageButton removeButton;
        private ImageButton addButton;

        private CartItem cartItem;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            this.initViews(itemView);
        }

        private void initViews(View view){
           cartTitle = view.findViewById(R.id.cart_item_name);
           cartPrice = view.findViewById(R.id.cart_item_price);
           quantity = view.findViewById(R.id.quantity);
           removeButton = view.findViewById(R.id.remove_one);
           addButton = view.findViewById(R.id.add_one);
        }

        public void setCartItem(CartItem cartItem) {
            this.cartItem = cartItem;
        }
    }
}
