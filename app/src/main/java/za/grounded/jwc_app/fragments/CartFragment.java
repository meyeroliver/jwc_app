package za.grounded.jwc_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.adapter.CartAdapter;
import za.grounded.jwc_app.viewmodels.CartViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CartViewModel cartViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(CartFragment.super.getContext()));
        recyclerView.setHasFixedSize(true);
        cartAdapter = new CartAdapter();
        recyclerView.setAdapter(cartAdapter);

        cartViewModel.getCartItemList().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null) {
                cartAdapter.setCartItemList(cartItems);
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        cartAdapter.getDeleteCartItem().observe(getViewLifecycleOwner(), integer -> {
            cartViewModel.deleteCartItem(integer);
        });

        cartAdapter.getAddItem().observe(getViewLifecycleOwner(), integer -> {
            cartViewModel.updateCartItemQuantity(integer, 1);
        });

        /**
         * Check if the current quantity is zero
         */
        cartAdapter.getRemoveItem().observe(getViewLifecycleOwner(), integer -> {
            cartViewModel.updateCartItemQuantity(integer, -1);
        });
    }
}
