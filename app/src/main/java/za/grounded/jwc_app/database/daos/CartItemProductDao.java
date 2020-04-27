package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import za.grounded.jwc_app.models.CartItemProduct;

@Dao
public interface CartItemProductDao {
   /* @Transaction
    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItemProduct>> getAllCartItems();*/
}
