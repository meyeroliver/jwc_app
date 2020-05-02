package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import za.grounded.jwc_app.database.repository.TransactionRepository;
import za.grounded.jwc_app.models.Transaction;

public class WelcomeViewModel extends AndroidViewModel {
    private TransactionRepository transactionRepository;

    public WelcomeViewModel(@NonNull Application application) {
        super(application);

        this.transactionRepository = new TransactionRepository(application);
    }

    public Long insertTransaction(Transaction transaction) {
        return this.transactionRepository.insertTransaction(transaction);
    }
}
