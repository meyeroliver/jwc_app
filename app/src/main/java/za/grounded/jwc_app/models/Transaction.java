package za.grounded.jwc_app.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int room_id;
    private String _id;
    private double total;
    private Date transactionDate;
}
