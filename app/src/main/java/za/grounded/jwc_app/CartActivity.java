package za.grounded.jwc_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import java.util.List;

import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.viewmodels.CartViewModel;

public class CartActivity extends AppCompatActivity {

    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        this.cartViewModel.getCartItemList().observe(this, cartItems -> {
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem.getProductId() + " : " + cartItem.getQuantity());
            }
        });
    }
}
