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
            "WHERE productId = :productId")
    void updateCartItemQuantity(String productId, int val);

    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getCartItemList();
}
