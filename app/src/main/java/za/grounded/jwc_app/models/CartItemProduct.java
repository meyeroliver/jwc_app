package za.grounded.jwc_app.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartItemProduct {
   /* @Embedded
    private Product product;
    @Relation(parentColumn = "_id",
                entityColumn = "productId")
    private CartItem cartItem;*/
}
