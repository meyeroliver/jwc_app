package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;

import za.grounded.jwc_app.models.Transaction;

@Dao
public interface TransactionDao {

    @Insert
    Long insertTransaction(Transaction transaction);

    @Query("UPDATE transactions " +
            "SET total = :total " +
            "WHERE room_id = :id")
    void updateTransactionTotal(Long id, Double total);

    @Query("UPDATE transactions " +
            "SET transactionDate = :transactionDate " +
            "WHERE room_id = :id")
    void updateTransactionDate(Long id, Date transactionDate);

    @Query("UPDATE transactions " +
            "SET _id = :mongo_id " +
            "WHERE room_id = :id")
    void updateTransactionMongoId(Long id, String mongo_id);

    /*@Query("SELECT * FROM")
    LiveData<Transaction> getTransactionById(int id);*/

}
