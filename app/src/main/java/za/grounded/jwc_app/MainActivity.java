package za.grounded.jwc_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.WorkInfo;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import za.grounded.jwc_app.adapter.TabAdapter;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.services.RetrofitClientInstance;
import za.grounded.jwc_app.viewmodels.LandingViewModel;

public class MainActivity extends AppCompatActivity {

    private LandingViewModel landingViewModel;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    //private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager);
        viewPager2.setAdapter(new TabAdapter(this));

        tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0 :
                            tab.setText("COMBO");
                            break;
                        case 1:
                            tab.setText("FULL");
                            break;
                        default:
                            tab.setText("HALF");
                            break;
                    }
                });
        tabLayoutMediator.attach();

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
