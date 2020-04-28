package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import za.grounded.jwc_app.models.CartItem;

@Dao
public interface CartItemDao {

    @Insert
    void insertCartItem(CartItem cartItem);

    @Query("UPDATE cart_items " +
            "SET quantity = quantity + :val " +
            "WHERE id = :id")
    void updateCartItemQuantity(int id, int val);

    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getCartItemList();

    @Query("DELETE FROM cart_items WHERE id = :id")
    void deleteCartItem(int id);
}
