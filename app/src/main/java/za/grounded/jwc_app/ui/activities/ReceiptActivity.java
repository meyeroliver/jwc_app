package za.grounded.jwc_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.fragments.ReceiptFragment;
import za.grounded.jwc_app.viewmodels.ReceiptViewModel;

public class ReceiptActivity extends AppCompatActivity {

    private ReceiptViewModel receiptViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        this.receiptViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        this.receiptViewModel.setTransactionId(getIntent().getLongExtra(getString(R.string.transaction_id), -1));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.receipt, new ReceiptFragment())
                .commit();


    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this, this.receiptViewModel.getTransactionId().toString(), Toast.LENGTH_SHORT).show();
    }
}
