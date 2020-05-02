package za.grounded.jwc_app.database.daos;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import za.grounded.jwc_app.models.TransactionAndCartItems;

@Dao
public interface TransactionCartItemDao {
    @Transaction
    @Query("SELECT * FROM transactions WHERE room_id = :id")
    TransactionAndCartItems getTransactionCartItems(int id);
}
