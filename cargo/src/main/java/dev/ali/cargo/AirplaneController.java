package dev.ali.cargo;

import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/airplanes")
@CrossOrigin(origins = "*")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

    @GetMapping
    public ResponseEntity<List<Airplane>> getAllAirplanes(){
        return new ResponseEntity<List<Airplane>>(airplaneService.allAirplanes(), HttpStatus.OK);
    }

    @GetMapping("/{airplaneId}")
    public ResponseEntity<Optional<Airplane>> getSingleAirplane(@PathVariable String airplaneId){
        ObjectId _airplaneId = new ObjectId(airplaneId);
        return new ResponseEntity<Optional<Airplane>>(airplaneService.singleAirplane(_airplaneId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Airplane> addCargoToPlane(@RequestBody Map<String, String> payload){
        ObjectId _airplaneId = new ObjectId(payload.get("airplaneId"));
        ObjectId _cargoId = new ObjectId(payload.get("cargoId"));
        return new ResponseEntity<Airplane>(airplaneService.addCargo(_airplaneId, _cargoId), HttpStatus.ACCEPTED);
    }
}
