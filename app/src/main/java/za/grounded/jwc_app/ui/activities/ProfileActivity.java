package za.grounded.jwc_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.fragments.CartFragment;
import za.grounded.jwc_app.ui.fragments.CheckOutFragment;
import za.grounded.jwc_app.ui.fragments.UserProfileFragment;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.user_profile, new UserProfileFragment())
                .commit();
    }
}
