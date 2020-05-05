package za.grounded.jwc_app.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.viewmodels.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FusedLocationProviderClient fusedLocationClient;

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

    }

    @Override
    public void onStart() {
        super.onStart();
        this.makeRequestForPlace();
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
            }
        });
    }

}
