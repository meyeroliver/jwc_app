package za.grounded.jwc_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import za.grounded.jwc_app.database.daos.CartItemDao;
import za.grounded.jwc_app.database.daos.ProductDao;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.Product;

@Database(entities = {Product.class, CartItem.class,}, version = 2, exportSchema = false)
public abstract class JWCDatabase extends RoomDatabase {
    private static JWCDatabase jwcDatabase;

    public abstract ProductDao productDao();
    public abstract CartItemDao cartItemDao();

    public static  synchronized JWCDatabase getJwcDatabase(Context context) {
        if (jwcDatabase == null) {
            jwcDatabase = Room.databaseBuilder(context, JWCDatabase.class, "JWC_Fisheries_DB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return jwcDatabase;
    }
}
