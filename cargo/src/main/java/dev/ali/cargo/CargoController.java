package dev.ali.cargo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cargoes")
@CrossOrigin(origins = "*")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<Cargo>> getAllCargoes(){
        return new ResponseEntity<List<Cargo>>(cargoService.allCargoes(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<List<Cargo>> getCompanyCargoes(@PathVariable String companyId){
        return new ResponseEntity<List<Cargo>>(cargoService.companyCargo(companyId), HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<Optional<Cargo>> getCargoById(@PathVariable String cargoId){
        ObjectId _cargoId = new ObjectId(cargoId);
        return new ResponseEntity<Optional<Cargo>>(cargoService.singleCargo(_cargoId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cargo> createCargo(@RequestBody Map<String, Object> payload){
        return new ResponseEntity<Cargo>(cargoService.createCargo((double) payload.get("size"), (String) payload.get("latestArrival"), (ObjectId) payload.get("companyId")), HttpStatus.CREATED);
    }
}
