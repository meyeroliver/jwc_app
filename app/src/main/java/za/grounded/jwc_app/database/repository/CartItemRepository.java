package za.grounded.jwc_app.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import lombok.NonNull;
import za.grounded.jwc_app.database.JWCDatabase;
import za.grounded.jwc_app.database.daos.CartItemDao;
import za.grounded.jwc_app.models.CartItem;

public class CartItemRepository {
    private CartItemDao cartItemDao;

    public CartItemRepository(Application application){
        this.cartItemDao = JWCDatabase.getJwcDatabase(application).cartItemDao();
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemDao.getCartItemList();
    }

    public void insertCartItem(CartItem cartItem) {
        InsertCartItem task = new InsertCartItem(cartItemDao);
        task.execute(cartItem);
    }

    private static class InsertCartItem extends AsyncTask<CartItem, Void, Void> {

        private CartItemDao cartItemDao;

        public InsertCartItem(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            this.cartItemDao.insertCartItem(cartItems[0]);
            return null;
        }
    }

}
