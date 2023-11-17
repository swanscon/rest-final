package com.astontech.resttest.repositories;

import com.astontech.resttest.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VehicleRepo extends CrudRepository<Vehicle, Integer> {

    Optional<Vehicle> findByVehicleVIN(String vin);
    Optional<Vehicle> findVehicleByVehicleVINOrId(String vin, Integer id);
}
