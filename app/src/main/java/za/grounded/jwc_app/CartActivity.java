package za.grounded.jwc_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import java.util.List;

import za.grounded.jwc_app.fragments.CartFragment;
import za.grounded.jwc_app.fragments.CheckOutFragment;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.CartItemProduct;
import za.grounded.jwc_app.viewmodels.CartViewModel;

public class CartActivity extends AppCompatActivity {

    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.cart, new CartFragment())
                .replace(R.id.checkout_view, new CheckOutFragment())
                .commit();
    }
}
