package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import za.grounded.jwc_app.models.Product;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM products WHERE category LIKE :category")
    LiveData<List<Product>> getProductListByCategory(String category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);
}
