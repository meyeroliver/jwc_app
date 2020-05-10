package za.grounded.jwc_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartItem(User user);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getLiveUserList();

    @Query("SELECT * FROM users")
    List<User> getUserList();

    @Query("SELECT username FROM users")
    List<String> getUsernameList();

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

}
