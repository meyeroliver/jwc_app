package za.grounded.jwc_app.database.daos;

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
    void updateTransactionTotal(int id, double total);

    @Query("UPDATE transactions " +
            "SET transactionDate = :transactionDate " +
            "WHERE room_id = :id")
    void updateTransactionDate(int id, Date transactionDate);

    @Query("UPDATE transactions " +
            "SET _id = :mongo_id " +
            "WHERE room_id = :id")
    void updateTransactionMongoId(int id, String mongo_id);

}
