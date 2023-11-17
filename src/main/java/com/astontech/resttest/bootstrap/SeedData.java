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
        loadSeedData();
    }

    private void loadSeedData() {
        if (vehicleMakeRepo.count() == 0) {
            VehicleMake ford = new VehicleMake("Ford");
            VehicleMake chevy = new VehicleMake("Chevy");
            vehicleMakeRepo.save(ford);
            vehicleMakeRepo.save(chevy);
            if(vehicleModelRepo.count() == 0) {
                VehicleModel f150 = new VehicleModel("F150", "https://d2ivfcfbdvj3sm.cloudfront.net/7fc965ab77efe6e0fa62e4ca1ea7673bb25e48540d1e3d8e88cb10/stills_0640_png/MY2021/14956/14956_st0640_116.png", ford);
                VehicleModel camaro = new VehicleModel("Camaro", "https://www.cars.com/i/large/in/v2/stock_photos/445dcc5d-5db2-4b8c-9fac-f3bcb4bd02bc/dff6f096-d77c-4884-9ccf-d9478e7bd56f.png", chevy);
                vehicleModelRepo.save(f150);
                vehicleModelRepo.save(camaro);
                if(vehicleRepo.count() == 0) {
                    Vehicle vehicle1 = new Vehicle("ABC123", "XYZ987", "2022", "Blue", f150);
                    Vehicle vehicle2 = new Vehicle("DEF456", "LMN654", "2023", "Red", camaro);
                    vehicleRepo.save(vehicle1);
                    vehicleRepo.save(vehicle2);
                }
            }
        }
    }
}
