package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import za.grounded.jwc_app.database.repository.CartItemRepository;
import za.grounded.jwc_app.database.repository.TransactionCartItemRepository;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.TransactionAndCartItems;

public class ReceiptViewModel extends AndroidViewModel {
    private CartItemRepository cartItemRepository;
    private TransactionCartItemRepository transactionCartItemRepository;

    public ReceiptViewModel(@NonNull Application application) {
        super(application);
        this.cartItemRepository = new CartItemRepository(application);
        this.transactionCartItemRepository = new TransactionCartItemRepository(application);
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemRepository.getCartItemList();
    }

    public TransactionAndCartItems getTransactionAndCartItemsById(int transactionId){
        return this.transactionCartItemRepository.getTransactionCartItemsById(transactionId);
    }
}
