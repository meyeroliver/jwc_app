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

    public LiveData<Double> calculateTotalCartPrice() {
        return this.cartItemDao.calculateTotalCartPrice();
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

    public void updateCartItemQuantity(Long id, Long val) {
        UpdateCartItemQuantity task = new UpdateCartItemQuantity(cartItemDao);
        Long[] longs = {id, val};
        task.execute(longs);

    }

    private static class UpdateCartItemQuantity extends AsyncTask <Long, Void, Void> {

        private CartItemDao cartItemDao;

        public UpdateCartItemQuantity(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }


        @Override
        protected Void doInBackground(Long... longs) {
            this.cartItemDao.updateCartItemQuantity(longs[0], longs[1]);
            return null;
        }
    }

    public void deleteCartItem(Long id) {
        DeleteCartItem task = new DeleteCartItem(cartItemDao);
        task.execute(id);
    }

    private static class DeleteCartItem extends AsyncTask<Long, Void, Void> {

        private CartItemDao cartItemDao;

        public DeleteCartItem(CartItemDao cartItemDao) {
            this.cartItemDao = cartItemDao;
        }

        @Override
        protected Void doInBackground(Long... longs) {
            this.cartItemDao.deleteCartItem(longs[0]);
            return null;
        }
    }
}
