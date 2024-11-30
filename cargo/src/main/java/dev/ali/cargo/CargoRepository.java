package dev.ali.cargo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends MongoRepository<Cargo, ObjectId> {
    List<Cargo> findCargoesByCompanyId(String companyId);
}
