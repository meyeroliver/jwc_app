package za.grounded.jwc_app.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @lombok.NonNull
    private String productId;
    @lombok.NonNull
    private int quantity;
}
