package za.grounded.jwc_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.WorkInfo;

import android.os.Bundle;

import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.services.RetrofitClientInstance;
import za.grounded.jwc_app.viewmodels.LandingViewModel;

public class MainActivity extends AppCompatActivity {

    private LandingViewModel landingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClientInstance.setRetrofitInstance();


        landingViewModel =  new ViewModelProvider(this).get(LandingViewModel.class);

        landingViewModel.getProductList().observe(this, workInfos -> {
            if (workInfos != null) {
                WorkInfo workInfo = workInfos.get(0);
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println("awe");
                } else if (workInfo.getState() == WorkInfo.State.FAILED){
                    System.out.println("not awe");
                }
            }
        });

        landingViewModel.getCategorizedProjectList("full").observe(this, products -> {
            for (Product product: products) {
                System.out.println(product.getCode() + " : " + product.getItem() + " : " + product.getCategory());
            }
        });

    }
}
