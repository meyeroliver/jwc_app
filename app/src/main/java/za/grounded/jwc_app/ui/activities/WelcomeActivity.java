package za.grounded.jwc_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.Transaction;
import za.grounded.jwc_app.viewmodels.WelcomeViewModel;

public class WelcomeActivity extends AppCompatActivity {

    private WelcomeViewModel welcomeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        this.welcomeViewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);
    }
    /**
     * -> create new cart when triggered
     */
    public void createNewCart(View view) {

        Long insertedTransaction =  this.welcomeViewModel.insertTransaction(new Transaction());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(getString(R.string.transaction_id), insertedTransaction);
        startActivity(intent);
    }
}
