package dev.ali.cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    @Id
    private ObjectId cargoId;
    private ObjectId companyId;
    private double size;
    private String latestArrival;

    public Cargo(double size, String latestArrival){
        this.size = size;
        this.latestArrival = latestArrival;
    }
}
