package za.grounded.jwc_app.models;

import androidx.room.Embedded;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String mongoId;
    private String googleId;
    private String name;
    private String surname;
    private String cellNumber;
    private String address;
    @Embedded
    private MyLocation location;
    private String username; // name + surname
    private String password; //use cell number
    private Boolean forgotPassword;
}
