package za.grounded.jwc_app.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import za.grounded.jwc_app.models.Product;

public interface JWCClientRetro {
    @GET("/products")
    Call<List<Product>> getProductList();
}
