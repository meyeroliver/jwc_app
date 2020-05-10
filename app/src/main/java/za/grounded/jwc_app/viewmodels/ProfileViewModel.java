package za.grounded.jwc_app.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import za.grounded.jwc_app.models.MyLocation;
import za.grounded.jwc_app.models.User;

public class ProfileViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> locationPermission = new MutableLiveData<>();
    private MutableLiveData<Boolean> createNewUser = new MutableLiveData<>();
    private MutableLiveData<Boolean> userInput = new MutableLiveData<>();
    private User newUser;
    private MyLocation myLocation;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public LiveData<Boolean> getLocationPermission() {
        return locationPermission;
    }

    public void setLocationPermission(boolean locationPermission) {
        this.locationPermission.setValue(locationPermission);
    }

    public LiveData<Boolean> getUserInput() {
        return userInput;
    }

    public void setUserInput(Boolean getuserInput) {
        this.userInput.setValue(getuserInput);
    }

    public LiveData<Boolean> getCreateNewUser() {
        return createNewUser;
    }

    public void setCreateNewUser(Boolean createNewUser) {
        this.createNewUser.setValue(createNewUser);
    }

    public MyLocation getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(MyLocation myLocation) {
        this.myLocation = myLocation;
    }

    public String validateIsEmpty(String userInput){
        if (userInput.isEmpty()) {
            return "User Input Required";
        }
        return null;
    }

    public String validCellNumber(String userInput) {
        if (userInput.length() == 10 || (userInput.length() ==  12 && userInput.startsWith("+27"))) {
            return null;
        } else{
            return "Invalid Number";
        }
    }

    public String validAddressLength(String userInput, List<String> compareTos) {
        if (userInput.length() >= 8) {
            boolean checker = false;
            for (String compare: compareTos) {
                if (userInput.toLowerCase().contains(compare)) {
                    checker = true;
                    break;
                }
            }
            return checker ? null : "Invalid Address";
        } else{
            return "Invalid Address";
        }
    }
}
