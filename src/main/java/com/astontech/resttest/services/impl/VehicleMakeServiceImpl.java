package com.astontech.resttest.services.impl;

import com.astontech.resttest.domain.VehicleMake;
import com.astontech.resttest.exceptions.EntityNotFoundException;
import com.astontech.resttest.exceptions.FieldNotFoundException;
import com.astontech.resttest.repositories.VehicleMakeRepo;
import com.astontech.resttest.services.VehicleMakeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    private VehicleMakeRepo vehicleMakeRepo;

    public VehicleMakeServiceImpl(VehicleMakeRepo vehicleMakeRepo) {
        this.vehicleMakeRepo = vehicleMakeRepo;
    }

    @Override
//    @Cacheable("vehicleMakeCache")
    public Iterable<VehicleMake> findAll() {
        return vehicleMakeRepo.findAll();
    }

    @Override
    public VehicleMake saveVehicleMake(VehicleMake vehicleMake) {
        return vehicleMakeRepo.save(vehicleMake);
    }

    @Override
//    @CacheEvict(value = "vehicleMakeCache", key = "#vehicleMake.id")
    public VehicleMake updateVehicleMake(VehicleMake vehicleMake) {
        return vehicleMakeRepo.save(vehicleMake);
    }

    @Override
    public VehicleMake patchVehicleMake(Map<String, Object> updates, Integer id) throws FieldNotFoundException{
        VehicleMake vehicleMakeToPatch = vehicleMakeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        updates.forEach((k, o) -> {
            System.out.println(k + ":" + o);
            try {
                Field nameField = vehicleMakeToPatch.getClass().getDeclaredField(k);
                nameField.setAccessible(true);
                nameField.set(vehicleMakeToPatch, o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new FieldNotFoundException(k);
            }
        });
        return vehicleMakeRepo.save(vehicleMakeToPatch);
    }

    @Override
//    @CacheEvict(value = "vehicleMakeCache", key = "#vehicleMake.id")
    public void deleteVehicleMake(VehicleMake vehicleMake) {
        vehicleMakeRepo.delete(vehicleMake);
    }

    @Override
//    @CacheEvict(value = "vehicleMakeCache", key = "#id")
    public void deleteVehicleMake(Integer id) {
        vehicleMakeRepo.deleteById(id);
    }
}
