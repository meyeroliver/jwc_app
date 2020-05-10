package za.grounded.jwc_app.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.models.MyLocation;
import za.grounded.jwc_app.models.User;
import za.grounded.jwc_app.ui.adapter.PlaceAutocompleteAdapterNew;
import za.grounded.jwc_app.viewmodels.ProfileViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    public static final String PLACES_TAG = "PLACES_TAG";
    public static final int ACCESS_FINE_LOCATION_PERMISSION = 100;

    private ProfileViewModel profileViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private PlacesClient placesClient;
    private RequestQueue queue;
    private AutocompleteSessionToken sessionToken;
    private FindCurrentPlaceRequest request;
    private PlaceAutocompleteAdapterNew placeAutocompleteAdapterNew;

    private TextInputLayout nameLayout;
    private TextView name;
    private TextInputLayout surnameLayout;
    private TextView surname;
    private TextInputLayout cellnumberLayout;
    private TextView cellphone;
    private TextInputLayout addressLayout;
    private TextView address;

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

        this.initViews(view);
        //this.initPlaces();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.makeRequestForPlace();
        this.consumeUserInput();
    }

    private void initViews(View view) {
        nameLayout = view.findViewById(R.id.nameLayout);
        name = view.findViewById(R.id.name);
        surnameLayout = view.findViewById(R.id.surnameLayout);
        surname = view.findViewById(R.id.surname);
        cellnumberLayout = view.findViewById(R.id.cell_number_layout);
        cellphone = view.findViewById(R.id.cell_number);
        addressLayout = view.findViewById(R.id.address_layout);
        address = view.findViewById(R.id.address);
    }

    private void consumeUserInput() {
        this.profileViewModel.getUserInput().observe(getActivity(), aBoolean -> {

            if (aBoolean != null) {
                if (aBoolean) {

                    resetTextInputLayout();

                    String name = this.name.getText().toString();
                    String surname = this.surname.getText().toString();
                    String cellphone = this.cellphone.getText().toString();
                    String address = this.address.getText().toString();


                    boolean nameEmpty = displayInputError(profileViewModel.validateIsEmpty(name), nameLayout);

                    boolean surnameEmpty = displayInputError(profileViewModel.validateIsEmpty(surname), surnameLayout);

                    boolean cellNumberEmpty = displayInputError(profileViewModel.validateIsEmpty(cellphone), cellnumberLayout);
                    boolean cellNumberNotValid = displayInputError(profileViewModel.validCellNumber(cellphone), cellnumberLayout);

                    boolean addressEmpty = displayInputError(profileViewModel.validateIsEmpty(address), addressLayout);
                    boolean addressNotValid = displayInputError(profileViewModel.validAddressLength(address,
                            Arrays.asList(getResources().getStringArray(R.array.address_validator_1))),
                            addressLayout);

                    if (!nameEmpty && !surnameEmpty && !cellNumberEmpty && !cellNumberNotValid
                    && !addressEmpty && !addressNotValid) {
                        User createNewUser = new User();
                        createNewUser.setName(name);
                        createNewUser.setSurname(surname);
                        createNewUser.setUsername(name + " " + surname);
                        createNewUser.setCellNumber(cellphone);
                        createNewUser.setAddress(address);
                        createNewUser.setLocation(profileViewModel.getMyLocation());
                        profileViewModel.setNewUser(createNewUser);
                        profileViewModel.setCreateNewUser(true);
                    }
                    profileViewModel.setUserInput(null);
                }
            }
        });
    }

    private boolean displayInputError(String validation, TextInputLayout textInputLayout) {
        if (validation != null) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError(validation);
            return true;
        }
        return false;
    }

    private void resetTextInputLayout() {
        nameLayout.setErrorEnabled(false);
        surnameLayout.setErrorEnabled(false);
        cellnumberLayout.setErrorEnabled(false);
        addressLayout.setErrorEnabled(false);
    }

    /**
     * This needs to happen only once for the duration of the app use session
     * use this with bundle, like the ramProtect method
     */
    private void initPlaces() {
        // Initialize Places.
        Places.initialize(Objects.requireNonNull(getContext()), ""/*getString(R.string.places_api_key)*/);

        // Create a new Places client instance.
        placesClient = Places.createClient(getContext());

        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME);

        // Use the builder to create a FindCurrentPlaceRequest.
        request = FindCurrentPlaceRequest.builder(placeFields).build();

        /******************************************************************************************/
        queue = Volley.newRequestQueue(getContext());

        AutocompleteSessionToken autocompleteSessionToken;
        autocompleteSessionToken=AutocompleteSessionToken.newInstance();
        placeAutocompleteAdapterNew = new PlaceAutocompleteAdapterNew(getContext(), placesClient, autocompleteSessionToken);
        //address.setAdapter(placeAutocompleteAdapterNew);
    }

    private void makeRequestForPlace() {
        // Use fields to define the data types to return.
        this.profileViewModel.getLocationPermission().observe(this, aBoolean -> {
            if (aBoolean != null) {

                if (aBoolean) {
                    getCurrentLocationFromGPS();
                }
            }
        });
    }

    private void getCurrentLocationFromGPS() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(Objects.requireNonNull(getActivity()), location -> {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        profileViewModel.setMyLocation(new MyLocation(location.getLatitude(), location.getLongitude()));
                    }
                });
    }

    private void getCurrentLocationFromPlacesApi() {
        placesClient.findCurrentPlace(request).addOnSuccessListener(((response) -> {
            for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                Log.i(PLACES_TAG, String.format("Place '%s' has likelihood: %f",
                        placeLikelihood.getPlace().getName(),
                        placeLikelihood.getLikelihood()));
            }
        })).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(PLACES_TAG, "Place not found: " + apiException.getStatusCode());
            }
        });
    }
}