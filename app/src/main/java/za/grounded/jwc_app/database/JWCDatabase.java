package za.grounded.jwc_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import za.grounded.jwc_app.database.converters.DateTypeConverters;
import za.grounded.jwc_app.database.daos.CartItemDao;
import za.grounded.jwc_app.database.daos.ProductDao;
import za.grounded.jwc_app.database.daos.TransactionCartItemDao;
import za.grounded.jwc_app.database.daos.TransactionDao;
import za.grounded.jwc_app.models.CartItem;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.models.Transaction;

@Database(entities = {Product.class, CartItem.class, Transaction.class}, version = 7, exportSchema = false)
@TypeConverters({DateTypeConverters.class})
public abstract class JWCDatabase extends RoomDatabase {
    private static JWCDatabase jwcDatabase;

    public abstract ProductDao productDao();
    public abstract CartItemDao cartItemDao();
    public abstract TransactionDao transactionDao();
    public abstract TransactionCartItemDao transactionCartItemDao();

    public static  synchronized JWCDatabase getJwcDatabase(Context context) {
        if (jwcDatabase == null) {
            jwcDatabase = Room.databaseBuilder(context, JWCDatabase.class, "JWC_Fisheries_DB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return jwcDatabase;
    }
}
