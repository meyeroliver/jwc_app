package za.grounded.jwc_app.ui.adapter;

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
    private MutableLiveData<String> addItem = new MutableLiveData<>();
    private MutableLiveData<String> removeItem = new MutableLiveData<>();
    private MutableLiveData<String> deleteCartItem = new MutableLiveData<>();



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
        holder.cartPrice.setText("R " + cartItem.getProduct().getPrice());
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

    public LiveData<String> getAddItem() {
        return addItem;
    }

    public void setAddItem(String addItem) {
        this.addItem.setValue(addItem);
    }

    public LiveData<String> getRemoveItem() {
        return removeItem;
    }

    public void setRemoveItem(String removeItem) {
        this.removeItem.setValue(removeItem);
    }

    public LiveData<String> getDeleteCartItem() {
        return deleteCartItem;
    }

    public void setDeleteCartItem(String deleteCartItem) {
        this.deleteCartItem.setValue(deleteCartItem);
    }

    public class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView cartTitle;
        private TextView cartPrice;
        private TextView quantity;
        private ImageButton removeOne;
        private ImageButton addOne;
        private ImageButton deleteCartItem;

        private CartItem cartItem;

        public CartHolder(@NonNull View itemView) {
            super(itemView);

            this.initViews(itemView);
            this.setOnClickListeners();
        }

        private void initViews(View view){
           cartTitle = view.findViewById(R.id.cart_item_name);
           cartPrice = view.findViewById(R.id.cart_item_price);
           quantity = view.findViewById(R.id.quantity);
           removeOne = view.findViewById(R.id.remove_one);
           addOne = view.findViewById(R.id.add_one);
           deleteCartItem = view.findViewById(R.id.delete_cart_item);
        }

        private void setOnClickListeners() {
            removeOne.setOnClickListener(this);
            addOne.setOnClickListener(this);
            deleteCartItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_one:
                    addItem.setValue(cartItem.getId());
                    break;
                case R.id.remove_one:
                    removeItem.setValue(cartItem.getId());
                    break;
                case R.id.delete_cart_item:
                    deleteItem();
                    break;
            }
        }

        private void deleteItem(){
            setDeleteCartItem(cartItem.getId());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(), cartItemList.size());
        }

        public void setCartItem(CartItem cartItem) {
            this.cartItem = cartItem;
        }
    }
}
