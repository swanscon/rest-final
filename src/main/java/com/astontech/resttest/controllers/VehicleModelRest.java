package com.astontech.resttest.controllers;

import com.astontech.resttest.domain.VehicleModel;
import com.astontech.resttest.services.VehicleModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehicle-models")
public class VehicleModelRest {

    private VehicleModelService vehicleModelService;

    public VehicleModelRest(VehicleModelService vehicleModelService) {
        this.vehicleModelService = vehicleModelService;
    }

    //region CRUD FUNCTIONS
    @GetMapping("/")
    public ResponseEntity<Iterable<VehicleModel>> getAllVehicleModels() {
        return ResponseEntity.ok(vehicleModelService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<VehicleModel> addVehicleModel(@RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(
                vehicleModelService.saveVehicleModel(vehicleModel),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<VehicleModel> updateVehicleModel(@RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(
                vehicleModelService.updateVehicleModel(vehicleModel),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleModel> partialUpdateVehicleModel(@RequestBody Map<String, Object> updates,
                                                                @PathVariable Integer id) {
        return new ResponseEntity<>(
                vehicleModelService.patchVehicleModel(updates, id),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteVehicle(@RequestBody VehicleModel vehicleModel) {
        vehicleModelService.deleteVehicleModel(vehicleModel);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleModelService.deleteVehicleModel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //end region
}
