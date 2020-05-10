package za.grounded.jwc_app.models;

import androidx.room.Entity;

import com.google.android.gms.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyLocation {
    private Double latitude;
    private Double longitude;
}

