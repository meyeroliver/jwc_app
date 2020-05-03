package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProfileViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> locationPermission = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<Boolean> getLocationPermission() {
        return locationPermission;
    }

    public void setLocationPermission(boolean locationPermission) {
        this.locationPermission.setValue(locationPermission);
    }
}
