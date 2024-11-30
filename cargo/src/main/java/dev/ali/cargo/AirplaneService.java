package dev.ali.cargo;

import com.mongodb.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Airplane> allAirplanes(){
        return airplaneRepository.findAll();
    }
    public Optional<Airplane> singleAirplane(ObjectId airplaneId){
        return airplaneRepository.findById(airplaneId);
    }
    private double currentCargoSize(@NonNull Airplane airplane){
        return airplane.getCargo().stream().mapToDouble(Cargo::getSize).sum();
    }

    private boolean hasCapacity(@NonNull Airplane airplane, double cargoSize){
        double currentCargo = currentCargoSize(airplane);
        return airplane.getCargoCapacity() >= currentCargo + cargoSize;
    }

    public Airplane addCargo(ObjectId airplaneId, ObjectId cargoId){
        try {
            Airplane airplane = airplaneRepository.findById(airplaneId).orElseThrow(Exception::new);
            Cargo cargo = cargoRepository.findById(cargoId).orElseThrow(Exception::new);
            double cargoSize = cargo.getSize();
            if (!hasCapacity(airplane, cargoSize)) {
                return null;
            }
            airplane.getCargo().add(cargo);
            airplaneRepository.save(airplane);
            return airplane;
        }
        catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }
}
