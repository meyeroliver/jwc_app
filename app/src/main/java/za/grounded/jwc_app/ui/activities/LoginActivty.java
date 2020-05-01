package za.grounded.jwc_app.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.adapter.TabAdapter;
import za.grounded.jwc_app.ui.fragments.SignInFragment;
import za.grounded.jwc_app.ui.fragments.SignUpFragment;

public class LoginActivty extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SignInFragment());
        fragmentList.add(new SignUpFragment());

        viewPager2 = findViewById(R.id.login_viewpager);
        viewPager2.setAdapter(new TabAdapter(this, fragmentList));

        tabLayout = findViewById(R.id.login_tabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0 :
                            tab.setText("LOGIN");
                            break;
                        case 1:
                            tab.setText("REGISTER");
                            break;
                    }
                });
        tabLayoutMediator.attach();

    }
}
