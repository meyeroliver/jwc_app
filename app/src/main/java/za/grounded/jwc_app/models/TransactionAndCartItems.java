package za.grounded.jwc_app.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransactionAndCartItems {
    @Embedded
    private Transaction transaction;
    @Relation(parentColumn = "room_id",
                entityColumn = "transaction_id", entity = CartItem.class)
    private List<CartItem> cartItemList;
}
