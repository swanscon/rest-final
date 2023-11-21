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
            VehicleMake toyota = new VehicleMake("Toyota");
            VehicleMake honda = new VehicleMake("Honda");
            VehicleMake bmw = new VehicleMake("BMW");
            VehicleMake mercedes = new VehicleMake("Mercedes");

            vehicleMakeRepo.save(ford);
            vehicleMakeRepo.save(chevy);
            vehicleMakeRepo.save(toyota);
            vehicleMakeRepo.save(honda);
            vehicleMakeRepo.save(bmw);
            vehicleMakeRepo.save(mercedes);

            if(vehicleModelRepo.count() == 0) {
                VehicleModel f150 = new VehicleModel("F150", "https://www.cars.com/i/large/in/v2/stock_photos/10e38ac5-b363-4a77-93a5-221773b0df83/610d1962-9b07-4e49-b6e1-8e57285703b2.png", ford);
                VehicleModel camaro = new VehicleModel("Camaro", "https://www.cars.com/i/large/in/v2/stock_photos/c7844db2-928b-4834-83a8-c127c74fe6e1/9e3a3745-da90-41c0-b93c-8c334093142e.png", chevy);
                VehicleModel corolla = new VehicleModel("Corolla","https://www.cars.com/i/large/in/v2/stock_photos/03879859-63e1-4d93-959b-ce57d8a403b1/0dbd67ec-6d76-4184-82b5-d01fa07f5065.png", toyota);
                VehicleModel accord = new VehicleModel("Accord", "https://www.cars.com/i/large/in/v2/stock_photos/e094f543-16b8-48e6-b3af-dfc584634c7a/39ad34ed-1dac-4889-8b0d-5e6e9170773c.png", honda);
                VehicleModel rav4 = new VehicleModel("RAV4", "https://www.cars.com/i/large/in/v2/stock_photos/4fed3aeb-3ea7-4803-aded-3dc11f2f1bf7/3e51eb95-3c65-4c56-bbc7-0669c76f20ce.png", toyota);
                VehicleModel series3 = new VehicleModel("3 Series", "https://www.cars.com/i/large/in/v2/stock_photos/b1695a6a-edc7-4c2f-a232-c473da9677fc/c13b3868-e5c7-4c03-b49e-4f563e519800.png", bmw);
                VehicleModel series5 = new VehicleModel("5 Series", "https://www.cars.com/i/large/in/v2/stock_photos/3311b9d2-fd89-4b5b-926e-5583b64fd559/47236056-5df7-49e0-9dc3-b5bcfbd8d86c.png", bmw);
                VehicleModel cClass = new VehicleModel("C Class", "https://www.cars.com/i/large/in/v2/stock_photos/77905074-efd3-4bc6-917e-7d150e5438a6/4c3f440e-2ba9-4e4d-a582-5c0b09390cd1.png", mercedes);
                VehicleModel eClass = new VehicleModel("E Class", "https://www.cars.com/i/large/in/v2/stock_photos/b469c38d-1036-41e6-b7f9-16f4fa383b19/4bce93b6-2ed9-4796-9407-5e27acc2e590.png", mercedes);

                vehicleModelRepo.save(f150);
                vehicleModelRepo.save(camaro);
                vehicleModelRepo.save(corolla);
                vehicleModelRepo.save(accord);
                vehicleModelRepo.save(rav4);
                vehicleModelRepo.save(series3);
                vehicleModelRepo.save(series5);
                vehicleModelRepo.save(cClass);
                vehicleModelRepo.save(eClass);

                if(vehicleRepo.count() == 0) {
                    Vehicle vehicle1 = new Vehicle("PZYU65RUJ4AF2SXTB", "I1DBECB", "2022", "Red", f150);
                    Vehicle vehicle2 = new Vehicle("YIW7S7PWKLRCOC148", "KODSX6Y", "2023", "White", camaro);
                    Vehicle vehicle3 = new Vehicle("UBH74G2YCI8567OJO", "3GR9NZD", "2019", "Black", corolla);
                    Vehicle vehicle4 = new Vehicle("6NP14C50R6DW46ZDA", "F0RT3ZP", "2020", "White", accord);
                    Vehicle vehicle5 = new Vehicle("REQFKG0H9AVUTCGMB", "DLUIBA9", "2018", "Silver", series3);
                    Vehicle vehicle6 = new Vehicle("3S8CNLOEFT5CGGJTK", "UQ4O2WE", "2021", "Black", cClass);

                    vehicleRepo.save(vehicle1);
                    vehicleRepo.save(vehicle2);
                    vehicleRepo.save(vehicle3);
                    vehicleRepo.save(vehicle4);
                    vehicleRepo.save(vehicle5);
                    vehicleRepo.save(vehicle6);

                }
            }
        }
    }

}
