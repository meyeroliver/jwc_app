package za.grounded.jwc_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import androidx.work.WorkInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.services.RetrofitClientInstance;
import za.grounded.jwc_app.ui.adapter.TabAdapter;
import za.grounded.jwc_app.ui.fragments.ComboMealFragment;
import za.grounded.jwc_app.ui.fragments.FullMealFragment;
import za.grounded.jwc_app.ui.fragments.HalfMealFragment;
import za.grounded.jwc_app.viewmodels.LandingViewModel;

public class MainActivity extends AppCompatActivity {

    private LandingViewModel landingViewModel;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Button viewCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.landingViewModel =  new ViewModelProvider(this).get(LandingViewModel.class);
        this.landingViewModel.setTransactionId(getIntent().getLongExtra(getString(R.string.transaction_id), -1));

        viewPager2 = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab_layout);
        viewCartButton = findViewById(R.id.view_cart);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ComboMealFragment());
        fragmentList.add(new FullMealFragment());
        fragmentList.add(new HalfMealFragment());
        viewPager2.setAdapter(new TabAdapter(this, fragmentList));


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
    }

    public void viewCart(View view) {
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        intent.putExtra(getString(R.string.transaction_id), this.landingViewModel.getTransactionId());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.landingViewModel.calculateTotalCartPrice().observe(this, aDouble -> {
            if (aDouble != null && aDouble > 0) {
                viewCartButton.setVisibility(View.VISIBLE);
            } else {
                viewCartButton.setVisibility(View.GONE);
            }
        });
    }
}
