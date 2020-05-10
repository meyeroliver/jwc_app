package za.grounded.jwc_app.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.User;
import za.grounded.jwc_app.ui.fragments.UserProfileFragment;
import za.grounded.jwc_app.viewmodels.ProfileViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

public class ProfileActivity extends AppCompatActivity {

    private static final int ACCESS_FINE_LOCATION_PERMISSION = 100;
    private ProfileViewModel profileViewModel;
    private Button createNewUserButton;
    private Button enableLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.user_profile, new UserProfileFragment())
                .commit();

        createNewUserButton = findViewById(R.id.create_user);
        enableLocationButton = findViewById(R.id.enable_location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.checkLocationPermissions();
    }

    private void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            this.profileViewModel.setLocationPermission(true);
        } else {
            getLocationPermission();
        }
    }

    private void getLocationPermission() {
        ActivityCompat.requestPermissions(ProfileActivity.this,
                new String[]{ACCESS_FINE_LOCATION},
                ACCESS_FINE_LOCATION_PERMISSION);
    }

    private void getInternetPermission() {
        /**
         * TODO: Check Internet Permission
         */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_FINE_LOCATION_PERMISSION) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.profileViewModel.setLocationPermission(true);

                this.createNewUserButton.setVisibility(View.VISIBLE);
                this.enableLocationButton.setVisibility(View.GONE);
            } else {
                this.profileViewModel.setLocationPermission(false);

                this.createNewUserButton.setVisibility(View.GONE);
                this.enableLocationButton.setVisibility(View.VISIBLE);
                Toast.makeText(ProfileActivity.this, "Access to your location is requires for delivery", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * TODO: check internet connection
     * TODO: validate user input
     * TODO: take user to recieptActivity to  confirm order
     *
     * @param view
     */
    public void createNewUser(View view) {

        this.profileViewModel.setUserInput(true);
        this.profileViewModel.getCreateNewUser().observe(this, aBoolean -> {

            if (aBoolean != null) {
                if (aBoolean) {
                    User user = profileViewModel.getNewUser();
                    profileViewModel.insertUser(user);

                    Toast.makeText(this, user.getUsername(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void enableLocation(View view) {
        //Toast.makeText(this, R.string.enable_device_location, Toast.LENGTH_SHORT).show();
        this.checkLocationPermissions();
    }
}