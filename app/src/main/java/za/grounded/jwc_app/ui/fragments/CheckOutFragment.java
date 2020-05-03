package za.grounded.jwc_app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.activities.ReceiptActivity;
import za.grounded.jwc_app.viewmodels.CartViewModel;

import static za.grounded.jwc_app.R.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment extends Fragment implements View.OnClickListener {

    private CartViewModel cartViewModel;
    private TextView totalTextView;
    private MaterialButton placeOrderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.cartViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(CartViewModel.class);

        View view = inflater.inflate(layout.fragment_check_out, container, false);
        totalTextView = view.findViewById(R.id.current_cart_price);
        placeOrderButton = view.findViewById(R.id.place_order_button);
        placeOrderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.cartViewModel.calculateTotalCartPrice().observe(getViewLifecycleOwner(), aDouble -> {
            Double val = aDouble;
            if (val == null)
                val = 0.0;
            totalTextView.setText("R " + val);
            cartViewModel.updateTransactionTotal(val);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.place_order_button) {
            System.out.println("awe mothersChild hond balf");
            this.placeOrder();
        }
    }

    /**
     * dialog for user to associate themselves to transaction
     *
     * check user.size > 0
     * true -> go to select user page
     * false -> go to create user page
     */
    private void placeOrder(){
        List<String> stringList = new ArrayList<>();
        stringList.add("User 1");
        stringList.add("User 2");
        stringList.add("User 3");
        stringList.add("User 4");
        stringList.add("new User");

        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                layout.select_user_item, id.user_item, stringList);

        new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setTitle(string.associate_user)
                .setAdapter(userAdapter, (dialog, which) -> {
                    if (!userAdapter.getItem(which).equals("new User")) {
                        Intent intent = new Intent(getActivity(), ReceiptActivity.class);
                        intent.putExtra(getString(string.transaction_id), this.cartViewModel.getTransactionId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Please add user info", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
