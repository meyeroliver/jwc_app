package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import za.grounded.jwc_app.database.daos.TransactionCartItemDao;
import za.grounded.jwc_app.database.repository.CartItemRepository;
import za.grounded.jwc_app.database.repository.TransactionCartItemRepository;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.TransactionAndCartItems;

public class CartViewModel extends AndroidViewModel {
    private CartItemRepository cartItemRepository;
    private TransactionCartItemRepository transactionCartItemRepository;
    private Long transactionId;

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.cartItemRepository = new CartItemRepository(application);
        this.transactionCartItemRepository = new TransactionCartItemRepository(application);
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LiveData<TransactionAndCartItems> getReactiveTransactionCartItems() {
        return this.transactionCartItemRepository.getReactiveTransactionCartItems(this.transactionId);
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemRepository.getCartItemList();
    }

    public void updateCartItemQuantity(String id, Long val) {
        this.cartItemRepository.updateCartItemQuantity(id, val);
    }

    public LiveData<Double> calculateTotalCartPrice() {
        return this.cartItemRepository.calculateTotalCartPrice();
    }

    public void deleteCartItem(String id) {
        this.cartItemRepository.deleteCartItem(id);
    }
}
