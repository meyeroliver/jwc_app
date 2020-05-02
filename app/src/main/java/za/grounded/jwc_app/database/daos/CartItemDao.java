package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import za.grounded.jwc_app.models.CartItem;

@Dao
public interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCartItem(CartItem cartItem);

    @Query("UPDATE cart_items " +
            "SET quantity = quantity + :val " +
            "WHERE id = :id")
    void updateCartItemQuantity(String id, Long val);

    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getCartItemList();

    @Query("DELETE FROM cart_items WHERE id = :id")
    void deleteCartItem(String id);

    @Query("SELECT SUM(quantity * price) AS TOTAL FROM cart_items WHERE transaction_id = :transactionId")
    LiveData<Double> calculateTotalCartPrice(Long transactionId);
}
