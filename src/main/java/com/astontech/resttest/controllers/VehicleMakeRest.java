package com.astontech.resttest.controllers;

import com.astontech.resttest.domain.VehicleMake;
import com.astontech.resttest.services.VehicleMakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehicle-makes")
public class VehicleMakeRest {

    private VehicleMakeService vehicleMakeService;

    public VehicleMakeRest(VehicleMakeService vehicleMakeService) {
        this.vehicleMakeService = vehicleMakeService;
    }

    //region CRUD FUNCTIONS
    @GetMapping("/")
    public ResponseEntity<Iterable<VehicleMake>> getAllVehicleMakes() {
        return ResponseEntity.ok(vehicleMakeService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<VehicleMake> addVehicleMake(@RequestBody VehicleMake vehicleMake) {
        return new ResponseEntity<>(
                vehicleMakeService.saveVehicleMake(vehicleMake),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<VehicleMake> updateVehicleMake(@RequestBody VehicleMake vehicleMake) {
        return new ResponseEntity<>(
                vehicleMakeService.updateVehicleMake(vehicleMake),
                HttpStatus.ACCEPTED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleMake> partialUpdateVehicleMake(@RequestBody Map<String, Object> updates,
                                                                @PathVariable Integer id) {
        return new ResponseEntity<>(
                vehicleMakeService.patchVehicleMake(updates, id),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteVehicle(@RequestBody VehicleMake vehicleMake) {
        vehicleMakeService.deleteVehicleMake(vehicleMake);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleMakeService.deleteVehicleMake(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //end region
}
