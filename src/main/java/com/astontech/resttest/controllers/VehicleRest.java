package com.astontech.resttest.controllers;

import com.astontech.resttest.domain.Vehicle;
import com.astontech.resttest.services.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehicleRest {

    private VehicleService vehicleService;

    public VehicleRest(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //region CRUD FUNCTIONS
    @GetMapping("/") //http://localhost:8080/vehicle/
    public ResponseEntity<Iterable<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(
                vehicleService.saveVehicle(vehicle),
                HttpStatus.CREATED
        );
    }

    // Idempotent - multiple requests will not change the system
    @PutMapping("/")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(
                vehicleService.updateVehicle(vehicle),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> partialUpdateVehicle(
            @RequestBody Map<String, Object> updates,
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                vehicleService.patchVehicle(updates, id),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.deleteVehicle(vehicle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //endregion

    //region QUERY PARAMETERS
    @GetMapping() //http://localhost:8080/vehicle?id=3
    public ResponseEntity<Vehicle> findByIdOrVin(@RequestParam(required = false) Integer id,
                                                 @RequestParam(required = false) String vin) {
        return ResponseEntity.ok(vehicleService.findVehicleByVehicleVINOrId(vin, id));
    }
    //endregion

    //region PATH PARAMETERS
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findByIdInPath(@PathVariable Integer id) {
        return ResponseEntity.ok(vehicleService.findVehicleByVehicleVINOrId(null, id));
    }
    //endregion
}
