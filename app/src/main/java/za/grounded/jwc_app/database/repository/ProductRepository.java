package za.grounded.jwc_app.database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import za.grounded.jwc_app.database.JWCDatabase;
import za.grounded.jwc_app.database.daos.ProductDao;
import za.grounded.jwc_app.models.Product;

public class ProductRepository {
    private ProductDao productDao;

    public ProductRepository(Application application) {
        this.productDao = JWCDatabase.getJwcDatabase(application).productDao();
    }

    public LiveData<List<Product>> getCategorizedProductList(String category) {
        return this.productDao.getProductListByCategory(category);
    }
}
