package za.grounded.jwc_app.ui.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.ui.activities.MainActivity;
import za.grounded.jwc_app.viewmodels.ProfileViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private PlacesClient placesClient;
    private FindCurrentPlaceRequest request;
    public static final String PLACES_TAG = "PLACES_TAG";
    public static final int ACCESS_FINE_LOCATION_PERMISSION = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        this.profileViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(ProfileViewModel.class);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initPlaces();

    }

    @Override
    public void onStart() {
        super.onStart();
        this.makeRequestForPlace();
    }

    /**
     * This needs to happen only once for the duration of the app use session
     * use this with bundle, like the ramProtect method
     */
    private void initPlaces() {
        // Initialize Places.
        /*Places.initialize(Objects.requireNonNull(getContext()), getString(R.string.places_api_key));

        // Create a new Places client instance.
        placesClient = Places.createClient(getContext());

        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME);

        // Use the builder to create a FindCurrentPlaceRequest.
        request = FindCurrentPlaceRequest.builder(placeFields).build();*/
    }

    private void makeRequestForPlace(){
        // Use fields to define the data types to return.
        this.profileViewModel.getLocationPermission().observe(this, aBoolean -> {
            if (aBoolean) {

                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(Objects.requireNonNull(getActivity()), location -> {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                System.out.println("Lat : " + location.getLatitude() + ", Lng : " + location.getLongitude());
                                Toast.makeText(getContext(), "Lat : " + location.getLatitude() + ", Lng : " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                            }
                        });
                /*placesClient.findCurrentPlace(request).addOnSuccessListener(((response) -> {
                    for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                        Log.i(PLACES_TAG, String.format("Place '%s' has likelihood: %f",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                       *//* textView.append(String.format("Place '%s' has likelihood: %f\n",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));*//*
                    }
                })).addOnFailureListener((exception) -> {
                    if (exception instanceof ApiException) {
                        ApiException apiException = (ApiException) exception;
                        Log.e(PLACES_TAG, "Place not found: " + apiException.getStatusCode());
                    }
                });*/
            }
        });
    }

}
