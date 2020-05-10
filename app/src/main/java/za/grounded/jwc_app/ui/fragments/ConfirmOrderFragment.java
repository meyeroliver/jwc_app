package za.grounded.jwc_app.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.viewmodels.ReceiptViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmOrderFragment extends Fragment implements View.OnClickListener {

    private TextView totalTextView;
    private MaterialButton placeOrderButton;
    private ReceiptViewModel receiptViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        receiptViewModel =  new ViewModelProvider(Objects.requireNonNull(getActivity())).get(ReceiptViewModel.class);

        View view = inflater.inflate(R.layout.fragment_check_out, container, false);
        totalTextView = view.findViewById(R.id.current_cart_price);
        placeOrderButton = view.findViewById(R.id.place_order_button);
        placeOrderButton.setText(R.string.confirm_order);
        placeOrderButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        this.receiptViewModel.calculateTotalCartPrice().observe(getViewLifecycleOwner(), aDouble -> {
            Double val = aDouble;
            if (val == null)
                val = 0.0;
            totalTextView.setText("R " + val);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.place_order_button) {
            Toast.makeText(getContext(), "Thank you for ordering.\n " +
                    "Someone will be with you shortly", Toast.LENGTH_LONG).show();
        }
    }
}
