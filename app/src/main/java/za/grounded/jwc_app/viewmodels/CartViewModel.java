package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import za.grounded.jwc_app.database.repository.CartItemRepository;
import za.grounded.jwc_app.models.CartItem;

public class CartViewModel extends AndroidViewModel {
    private CartItemRepository cartItemRepository;
    private Long transactionId;

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.cartItemRepository = new CartItemRepository(application);
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemRepository.getCartItemList();
    }

    public void updateCartItemQuantity(Long id, Long val) {
        this.cartItemRepository.updateCartItemQuantity(id, val);
    }

    public LiveData<Double> calculateTotalCartPrice() {
        return this.cartItemRepository.calculateTotalCartPrice();
    }

    public void deleteCartItem(Long id) {
        this.cartItemRepository.deleteCartItem(id);
    }
}
