package za.grounded.jwc_app.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

import za.grounded.jwc_app.database.JWCDatabase;
import za.grounded.jwc_app.database.daos.TransactionDao;
import za.grounded.jwc_app.models.Transaction;

public class TransactionRepository {

    private TransactionDao transactionDao;

    public TransactionRepository(Application application) {
        this.transactionDao = JWCDatabase.getJwcDatabase(application).transactionDao();
    }

    public Long insertTransaction(Transaction transaction){
        InsertTransaction task = new InsertTransaction(this.transactionDao);
        task.execute(transaction);
        try {
            return task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InsertTransaction extends AsyncTask<Transaction, Void, Long> {
        private TransactionDao transactionDao;

        InsertTransaction(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Long doInBackground(Transaction... transactions) {
            return this.transactionDao.insertTransaction(transactions[0]);
        }
    }

    public void updateTransactionTotal(Long id, Double total){
        UpdateTransactionTotal task = new UpdateTransactionTotal(this.transactionDao);
        task.execute(new Transaction(id, total));
    }

    private static class UpdateTransactionTotal extends AsyncTask<Transaction, Void, Void> {
        private TransactionDao transactionDao;

        public UpdateTransactionTotal(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(Transaction... transactions) {

            Transaction transaction =  transactions[0];
            this.transactionDao.updateTransactionTotal(transaction.getRoom_id(), transaction.getTotal());
            return null;
        }
    };
}
