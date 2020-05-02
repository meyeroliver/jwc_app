package za.grounded.jwc_app.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.concurrent.ExecutionException;

import za.grounded.jwc_app.database.JWCDatabase;
import za.grounded.jwc_app.database.daos.TransactionCartItemDao;
import za.grounded.jwc_app.models.TransactionAndCartItems;

public class TransactionCartItemRepository {

    private TransactionCartItemDao transactionCartItemDao;

    public TransactionCartItemRepository(Application application) {
        this.transactionCartItemDao = JWCDatabase.getJwcDatabase(application).transactionCartItemDao();
    }

    public LiveData<TransactionAndCartItems> getReactiveTransactionCartItems(Long cartId) {
        return this.transactionCartItemDao.getReactiveTransactionCartItems(cartId);
    }

    public TransactionAndCartItems getTransactionCartItemsById(Long transactionId){
        GetTransTransactionCartItemsById task = new GetTransTransactionCartItemsById(this.transactionCartItemDao);
        task.execute(transactionId);
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class GetTransTransactionCartItemsById extends AsyncTask<Long, Void, TransactionAndCartItems> {

        private TransactionCartItemDao transactionCartItemDao;

        GetTransTransactionCartItemsById(TransactionCartItemDao transactionCartItemDao) {
            this.transactionCartItemDao = transactionCartItemDao;
        }

        @Override
        protected TransactionAndCartItems doInBackground(Long... longs) {
            return this.transactionCartItemDao.getTransactionCartItems(longs[0]);
        }
    }
}
