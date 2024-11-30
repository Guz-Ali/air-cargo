package dev.ali.cargo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "airplane")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {
    @Id
    private ObjectId airplaneId;
    private String type;
//    @DocumentReference
//    private Airline airline;
    private double cargoCapacity;
    @DocumentReference
    private List<Cargo> cargo;

}
