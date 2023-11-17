package com.astontech.resttest.repositories;

import com.astontech.resttest.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface VehicleModelRepo extends CrudRepository<VehicleModel, Integer> {

    Optional<VehicleModel> findVehicleModelByVehicleModelName(String name);
}
