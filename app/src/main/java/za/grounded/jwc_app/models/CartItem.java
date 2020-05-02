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
    @NonNull
    @PrimaryKey
    @androidx.annotation.NonNull
    private String id;
    @NonNull
    private Long transaction_id;
    @NonNull
    @Embedded
    private Product product;
    @NonNull
    private Long quantity;

    public CartItem(@androidx.annotation.NonNull @NonNull String id, @NonNull Long quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
