package com.astontech.resttest.bootstrap;

import com.astontech.resttest.domain.Vehicle;
import com.astontech.resttest.domain.VehicleMake;
import com.astontech.resttest.domain.VehicleModel;
import com.astontech.resttest.repositories.VehicleMakeRepo;
import com.astontech.resttest.repositories.VehicleModelRepo;
import com.astontech.resttest.repositories.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleModelRepo vehicleModelRepo;

    @Autowired
    VehicleMakeRepo vehicleMakeRepo;

    @Override
    public void run(String... args) throws Exception {
        loadVehicleMakeData();
        loadVehicleModelData();
        loadVehicleData();
    }

    private void loadVehicleMakeData() {
        if (vehicleMakeRepo.count() == 0) {
            VehicleMake vehicleMake1 = new VehicleMake("Ford");
            VehicleMake vehicleMake2 = new VehicleMake("Chevy");
            vehicleMakeRepo.save(vehicleMake1);
            vehicleMakeRepo.save(vehicleMake2);
        }
    }

    private void loadVehicleModelData() {
        if (vehicleModelRepo.count() == 0) {
            VehicleModel vehicleModel1 = new VehicleModel("F150", 1);
            VehicleModel vehicleModel2 = new VehicleModel("Camaro", 2);
            vehicleModelRepo.save(vehicleModel1);
            vehicleModelRepo.save(vehicleModel2);
        }
    }

    private void loadVehicleData() {
        if (vehicleRepo.count() == 0) {
            Vehicle vehicle1 = new Vehicle("ABC123", "XYZ987", "2022", "Blue", 1);
            Vehicle vehicle2 = new Vehicle("DEF456", "LMN654", "2023", "Red", 2);
            vehicleRepo.save(vehicle1);
            vehicleRepo.save(vehicle2);
        }
    }
}
