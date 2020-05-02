package za.grounded.jwc_app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.activities.ReceiptActivity;
import za.grounded.jwc_app.viewmodels.CartViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckOutFragment extends Fragment {

    private CartViewModel cartViewModel;
    private TextView totalTextView;
    private MaterialButton placeOrderButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.cartViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(CartViewModel.class);

        View view = inflater.inflate(R.layout.fragment_check_out, container, false);
        totalTextView = view.findViewById(R.id.current_cart_price);
        placeOrderButton = view.findViewById(R.id.place_order_button);
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

        placeOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ReceiptActivity.class);
            intent.putExtra(getString(R.string.transaction_id), this.cartViewModel.getTransactionId());
            startActivity(intent);
        });
    }
}
