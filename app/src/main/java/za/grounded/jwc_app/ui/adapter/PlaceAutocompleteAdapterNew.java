package za.grounded.jwc_app.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.R;

public class PlaceAutocompleteAdapterNew extends ArrayAdapter<AutocompletePrediction> implements Filterable {
    PlacesClient placesClient;
    AutocompleteSessionToken token;
    private static final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);

    private List<AutocompletePrediction> mResultList = new ArrayList<>();
    private List<AutocompletePrediction> tempResult = new ArrayList<>();


    Context context;
    private String TAG = "PlaceAutoCompleteAdapter";

    public PlaceAutocompleteAdapterNew(Context context, PlacesClient placesClient, AutocompleteSessionToken token) {
        super(context, R.layout.place_prediction_item, R.id.text_view_title);
        this.context = context;
        this.placesClient = placesClient;
        this.token = token;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        View row = super.getView(position, convertView, parent);
        TextView title = (TextView) row.findViewById(R.id.text_view_title);
        TextView address = row.findViewById(R.id.text_view_address);

        AutocompletePrediction item = getItem(position);
        if (item == null) {
            title.setText(R.string.out_bounds);
            address.setText(R.string.unable_to_deliver);
        } else {
            title.setText(item.getPrimaryText(STYLE_BOLD));
            address.setText(item.getSecondaryText(new StyleSpan(Typeface.NORMAL)));
        }
        return row;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public AutocompletePrediction getItem(int position) {
        try {
            return mResultList.get(position);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // Skip the autocomplete query if no constraints are given.
                if (constraint != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    mResultList = getAutoComplete(constraint);
                    if (mResultList != null) {
                        // The API successfully returned results.
                        results.values = mResultList;
                        results.count = mResultList.size();
                        if (constraint.length() > 6 && results.count == 0) {
                            Toast.makeText(context, R.string.unable_to_deliver, Toast.LENGTH_LONG).show();
                        }
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    // The API returned at least one result, update the data.
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                // Override this method to display a readable result in the AutocompleteTextView
                // when clicked.
                if (resultValue instanceof AutocompletePrediction) {
                    return ((AutocompletePrediction) resultValue).getFullText(null);
                } else {
                    return super.convertResultToString(resultValue);
                }
            }
        };
    }

    private List<AutocompletePrediction> getAutoComplete(CharSequence constraint) {
        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
        // and once again when the user makes a selection (for example when calling fetchPlace()).
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
        // Create a RectangularBounds object.

        // Use the builder to create a FindAutocompletePredictionsRequest.
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setCountries("ZA")
                .setLocationRestriction(RectangularBounds.newInstance(
                        new LatLng(-33.954167, 18.656638),
                        new LatLng(-33.935369, 18.601191)))
                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(constraint.toString())
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener(response -> {
            tempResult = response.getAutocompletePredictions();
        }).addOnFailureListener(exception -> {
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
            }
        });
        return tempResult;
    }
}