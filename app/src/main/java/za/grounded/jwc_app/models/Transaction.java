package za.grounded.jwc_app.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private Long room_id;
    private String _id;
    private Double total;
    private Date transactionDate;

    public Transaction(Long room_id, Double total) {
        this.room_id = room_id;
        this.total = total;
    }
}
