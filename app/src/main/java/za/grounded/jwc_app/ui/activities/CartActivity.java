package za.grounded.jwc_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.fragments.CartFragment;
import za.grounded.jwc_app.ui.fragments.CheckOutFragment;
import za.grounded.jwc_app.viewmodels.CartViewModel;

public class CartActivity extends AppCompatActivity {

    private CartViewModel cartViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        this.cartViewModel.setTransactionId(getIntent().getLongExtra(getString(R.string.transaction_id), -1));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.cart, new CartFragment())
                .replace(R.id.checkout_view, new CheckOutFragment())
                .commit();
    }
}
