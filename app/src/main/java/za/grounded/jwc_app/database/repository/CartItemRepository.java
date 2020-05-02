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

    public LiveData<Double> calculateTotalCartPrice(Long transactionId) {
        return this.cartItemDao.calculateTotalCartPrice(transactionId);
    }


    public void insertCartItem(CartItem cartItem) {
        InsertCartItem task = new InsertCartItem(cartItemDao);
        task.execute(cartItem);
    }

    private static class InsertCartItem extends AsyncTask<CartItem, Void, Void> {

        private CartItemDao cartItemDao;

        InsertCartItem(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            this.cartItemDao.insertCartItem(cartItems[0]);
            return null;
        }
    }

    public void updateCartItemQuantity(String id, Long quantity) {
        UpdateCartItemQuantity task = new UpdateCartItemQuantity(cartItemDao);
        task.execute(new CartItem(id, quantity));
    }

    private static class UpdateCartItemQuantity extends AsyncTask <CartItem, Void, Void> {

        private CartItemDao cartItemDao;

        UpdateCartItemQuantity(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            CartItem cartItem = cartItems[0];
            this.cartItemDao.updateCartItemQuantity(cartItem.getId(), cartItem.getQuantity());
            return null;
        }
    }

    public void deleteCartItem(String id) {
        DeleteCartItem task = new DeleteCartItem(cartItemDao);
        task.execute(id);
    }

    private static class DeleteCartItem extends AsyncTask<String, Void, Void> {

        private CartItemDao cartItemDao;

        DeleteCartItem(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            this.cartItemDao.deleteCartItem(strings[0]);
            return null;
        }
    }
}
