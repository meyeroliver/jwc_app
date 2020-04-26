package za.grounded.jwc_app.services.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.services.JWCClientRetro;
import za.grounded.jwc_app.services.RetrofitClientInstance;

public class GetProductListWorker extends Worker {

    private JWCClientRetro jwcClientRetro;

    public GetProductListWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.jwcClientRetro = RetrofitClientInstance.getJWCClient();
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            Response<List<Product>> response = this.jwcClientRetro.getProductList().execute();
            if (response.isSuccessful()) {
                if (response.body() != null ) {
                    for (Product product: response.body()) {
                        System.out.println(product.getCode() + " : " + product.getItem());
                    }
                    return Result.success();
                }
                return Result.success();
            } else {
                return Result.success();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}
