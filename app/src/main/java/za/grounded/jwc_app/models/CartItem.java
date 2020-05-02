package za.grounded.jwc_app.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(tableName = "cart_items")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int transaction_id;
    @NonNull
    @Embedded
    private Product product;
    @NonNull
    private int quantity;
}
