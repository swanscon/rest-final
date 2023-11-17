package com.astontech.resttest.services;

import com.astontech.resttest.domain.VehicleModel;

import java.util.Map;

public interface VehicleModelService {

    VehicleModel findVehicleModelByVehicleModelName(String name);
    Iterable<VehicleModel> findAll();
    VehicleModel saveVehicleModel(VehicleModel vehicleModel);
    VehicleModel updateVehicleModel(VehicleModel vehicleModel);
    VehicleModel patchVehicleModel(Map<String, Object> updates, Integer id);
    void deleteVehicleModel(VehicleModel vehicleModel);
    void deleteVehicleModel(Integer id);
}
