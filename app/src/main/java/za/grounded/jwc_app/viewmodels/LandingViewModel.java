package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.WorkInfo;

import java.util.List;

import za.grounded.jwc_app.database.repository.CartItemRepository;
import za.grounded.jwc_app.database.repository.ProductRepository;
import za.grounded.jwc_app.database.repository.TransactionCartItemRepository;
import za.grounded.jwc_app.database.repository.TransactionRepository;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.models.TransactionAndCartItems;
import za.grounded.jwc_app.services.repository.JWCRepository;

public class LandingViewModel extends AndroidViewModel {

    private JWCRepository jwcRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;
    private TransactionRepository transactionRepository;
    private TransactionCartItemRepository transactionCartItemRepository;
    private Long transactionId;

    public LandingViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application);
        this.cartItemRepository = new CartItemRepository(application);
        this.transactionRepository = new TransactionRepository(application);
        this.transactionCartItemRepository =  new TransactionCartItemRepository(application);
        this.jwcRepository = new JWCRepository(application);
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LiveData<List<WorkInfo>> getProductList() {
        return jwcRepository.getProductList();
    }

    public LiveData<List<Product>> getCategorizedProjectList(String category) {
        return this.productRepository.getCategorizedProductList(category);
    }

    public void insertCartItem(Product product){
        this.cartItemRepository.insertCartItem(new CartItem(product.get_id() ,this.transactionId, product, 1L));
    }

    public LiveData<TransactionAndCartItems> getReactiveTransactionCartItems() {
        return this.transactionCartItemRepository.getReactiveTransactionCartItems(this.transactionId);
    }

    public LiveData<List<CartItem>> getCartItemList(){
        return this.cartItemRepository.getCartItemList();
    }

    public LiveData<Double> calculateTotalCartPrice() {
        return this.cartItemRepository.calculateTotalCartPrice(transactionId);
    }
}
