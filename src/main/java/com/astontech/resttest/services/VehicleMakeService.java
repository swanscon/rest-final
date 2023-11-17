package com.astontech.resttest.services;

import com.astontech.resttest.domain.VehicleMake;

import java.util.Map;

public interface VehicleMakeService {

    Iterable<VehicleMake> findAll();
    VehicleMake saveVehicleMake(VehicleMake vehicleMake);
    VehicleMake updateVehicleMake(VehicleMake vehicleMake);
    VehicleMake patchVehicleMake(Map<String, Object> updates, Integer id);
    void deleteVehicleMake(VehicleMake vehicleMake);
    void deleteVehicleMake(Integer id);
}
