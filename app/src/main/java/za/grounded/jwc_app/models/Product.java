package za.grounded.jwc_app.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(tableName = "products")
public class Product {
    @PrimaryKey
    @NonNull
    private String _id;
    private String code;
    private String item;
    private String category;
    private Double price;
}
