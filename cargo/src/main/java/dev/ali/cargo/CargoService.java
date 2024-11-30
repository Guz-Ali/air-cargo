package dev.ali.cargo;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Cargo> allCargoes(){
        return cargoRepository.findAll();
    }

    public List<Cargo> companyCargo(String companyId){
        return cargoRepository.findCargoesByCompanyId(companyId);
    }

    public Optional<Cargo> singleCargo(ObjectId cargoId){
        return cargoRepository.findById(cargoId);
    }

    public Cargo createCargo(double size, String latestArrival, ObjectId companyId){
        Cargo cargo = cargoRepository.insert(new Cargo(size, latestArrival));
        mongoTemplate.update(Company.class)
                .matching(Criteria.where("companyId").is(companyId))
                .apply(new Update().push("cargo").value(cargo))
                .first();
        return cargo;
    }
}
