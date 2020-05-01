package za.grounded.jwc_app.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.CartItem;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptHolder>{

    List<CartItem> cartItemList = new ArrayList<>();

    @NonNull
    @Override
    public ReceiptHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_item, parent, false);

        return new ReceiptHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptHolder holder, int position) {
        CartItem cartItem = this.cartItemList.get(position);
        holder.titleTextView.setText(cartItem.getProduct().getItem());
        holder.priceTextView.setText("R " + cartItem.getProduct().getPrice());
        holder.qtyTextView.setText("" + cartItem.getQuantity());
        holder.costTextView.setText("R" + (cartItem.getProduct().getPrice() * cartItem.getQuantity()));
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

    public class ReceiptHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView priceTextView;
        private TextView qtyTextView;
        private TextView costTextView;

        public ReceiptHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View view) {
            titleTextView = view.findViewById(R.id.receipt_title);
            priceTextView = view.findViewById(R.id.receipt_price);
            qtyTextView = view.findViewById(R.id.receipt_quantity);
            costTextView = view.findViewById(R.id.receipt_cost);
        }
    }
}
