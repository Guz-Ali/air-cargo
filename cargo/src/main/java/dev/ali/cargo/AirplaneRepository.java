package dev.ali.cargo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AirplaneRepository extends MongoRepository<Airplane, ObjectId> {
}
