package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.WorkInfo;

import java.util.List;

import za.grounded.jwc_app.database.repository.ProductRepository;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.services.repository.JWCRepository;

public class LandingViewModel extends AndroidViewModel {

    private JWCRepository jwcRepository;
    private ProductRepository productRepository;

    public LandingViewModel(@NonNull Application application) {
        super(application);
        this.productRepository = new ProductRepository(application);
        this.jwcRepository = new JWCRepository(application);
    }

    public LiveData<List<WorkInfo>> getProductList() {
        return jwcRepository.getProductList();
    }

    public LiveData<List<Product>> getCategorizedProjectList(String category) {
        return this.productRepository.getCategorizedProductList(category);
    }
}
