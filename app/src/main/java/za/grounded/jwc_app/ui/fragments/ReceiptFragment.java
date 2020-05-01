package za.grounded.jwc_app.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.adapter.ReceiptAdapter;
import za.grounded.jwc_app.viewmodels.ReceiptViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiptFragment extends Fragment {

    private RecyclerView recyclerView;
    private ReceiptAdapter receiptAdapter;
    private ReceiptViewModel receiptViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        receiptViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(ReceiptViewModel.class);

        View view  = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(ReceiptFragment.super.getContext()));
        recyclerView.setHasFixedSize(true);
        receiptAdapter = new ReceiptAdapter();
        recyclerView.setAdapter(receiptAdapter);

        receiptViewModel.getCartItemList().observe(getViewLifecycleOwner(), cartItems -> {
            if (cartItems != null) {
                receiptAdapter.setCartItemList(cartItems);
                receiptAdapter.notifyDataSetChanged();
            }
        });
    }

}
