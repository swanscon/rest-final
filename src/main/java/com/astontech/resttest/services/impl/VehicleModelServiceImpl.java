package com.astontech.resttest.services.impl;

import com.astontech.resttest.domain.Vehicle;
import com.astontech.resttest.domain.VehicleMake;
import com.astontech.resttest.domain.VehicleModel;
import com.astontech.resttest.exceptions.EntityAlreadyExistsException;
import com.astontech.resttest.exceptions.EntityNotFoundException;
import com.astontech.resttest.exceptions.FieldNotFoundException;
import com.astontech.resttest.repositories.VehicleModelRepo;
import com.astontech.resttest.services.VehicleModelService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    private VehicleModelRepo vehicleModelRepo;

    private VehicleModelServiceImpl(VehicleModelRepo vehicleModelRepo) {
        this.vehicleModelRepo = vehicleModelRepo;
    }

    @Override
    public VehicleModel findVehicleModelByVehicleModelName(String name) {
        return vehicleModelRepo.findVehicleModelByVehicleModelName(name)
                .orElseThrow(() -> new EntityNotFoundException(name));
    }

    @Override
    public Iterable<VehicleModel> findAll() {
        return vehicleModelRepo.findAll();
    }

    @Override
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
//        String name = vehicleModel.getVehicleModelName();
//        VehicleModel newVehicleModel = vehicleModelRepo.findVehicleModelByVehicleModelName(name)
//                .orElse(new VehicleModel());
//        if(newVehicleModel.getVehicleModelName().equals(name)) {
//            throw new EntityAlreadyExistsException(name);
//        }
        return vehicleModelRepo.save(vehicleModel);
    }

    @Override
//    @CacheEvict(value = "vehicleModelCache", key = "#vehicleModel.id")
    public VehicleModel updateVehicleModel(VehicleModel vehicleModel) {
        return vehicleModelRepo.save(vehicleModel);
    }

    @Override
//    @CacheEvict(value = "vehicleModelCache", key = "#id")
    public VehicleModel patchVehicleModel(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        VehicleModel vehicleModelToPatch = vehicleModelRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        updates.forEach((k, o) -> {
            System.out.println(k + ":" + o);
            try {
                Field nameField = vehicleModelToPatch.getClass().getDeclaredField(k);
                nameField.setAccessible(true);
                nameField.set(vehicleModelToPatch, o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new FieldNotFoundException(k);
            }
        });
        return vehicleModelRepo.save(vehicleModelToPatch);
    }

    @Override
//    @CacheEvict(value = "vehicleModelCache", key = "#vehicleModel.id")
    public void deleteVehicleModel(VehicleModel vehicleModel) {
        vehicleModelRepo.delete(vehicleModel);
    }

    @Override
//    @CacheEvict(value = "vehicleModelCache", key = "#id")
    public void deleteVehicleModel(Integer id) {
        vehicleModelRepo.deleteById(id);
    }
}
