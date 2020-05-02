package za.grounded.jwc_app.models;

import androidx.room.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MyLocation {
    private Double latitude;
    private Double longitude;
}
