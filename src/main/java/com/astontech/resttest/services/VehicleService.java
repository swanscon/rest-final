package com.astontech.resttest.services;

import com.astontech.resttest.domain.Vehicle;

import java.util.Map;

public interface VehicleService {

    Vehicle findVehicleByVehicleVIN(String vin);
    Vehicle findVehicleByVehicleVINOrId(String vin, Integer id);

    Iterable<Vehicle> findAll();
    Vehicle saveVehicle(Vehicle vehicle);
    Vehicle updateVehicle(Vehicle vehicle);
    Vehicle patchVehicle(Map<String, Object> updates, Integer id);
    void deleteVehicle(Vehicle vehicle);
    void deleteVehicle(Integer id);
}
