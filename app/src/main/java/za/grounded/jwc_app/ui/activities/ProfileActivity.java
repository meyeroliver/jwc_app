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
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.fragments.UserProfileFragment;
import za.grounded.jwc_app.viewmodels.ProfileViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class ProfileActivity extends AppCompatActivity {

    private static final int ACCESS_FINE_LOCATION_PERMISSION = 100;
    private ProfileViewModel profileViewModel;

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

    /*private void setUpAutoCompleteWidget() {
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        assert autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS));
        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(-34.117663, 24.873028),
                new LatLng(-33.955058, 24.729956)
        ));
        autocompleteFragment.setCountry("ZA");
        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("PLACES", "Place: " + place.getName() +
                        ", ID : " + place.getId() + ", Address :" + place.getAddress());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("PLACES", "An error occurred: " + status);
            }
        });
    }
*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACCESS_FINE_LOCATION_PERMISSION) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.profileViewModel.setLocationPermission(true);
            } else {
                this.profileViewModel.setLocationPermission(false);
                Toast.makeText(ProfileActivity.this, "Access to your location is needed for delivery", Toast.LENGTH_LONG).show();
            }
        }
    }
}