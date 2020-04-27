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

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.cartItemRepository = new CartItemRepository(application);
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemRepository.getCartItemList();
    }
}
