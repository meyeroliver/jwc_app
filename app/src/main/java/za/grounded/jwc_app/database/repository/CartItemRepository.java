package za.grounded.jwc_app.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

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

    public void deleteCartItem(int id) {
        DeleteCartItem task = new DeleteCartItem(cartItemDao);
        task.execute(id);
    }

    private static class DeleteCartItem extends AsyncTask<Integer, Void, Void> {

        private CartItemDao cartItemDao;

        public DeleteCartItem(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            this.cartItemDao.deleteCartItem(integers[0]);
            return null;
        }
    }
}
