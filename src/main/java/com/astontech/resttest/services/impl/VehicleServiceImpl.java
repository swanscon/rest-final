package com.astontech.resttest.services.impl;

import com.astontech.resttest.domain.Vehicle;
import com.astontech.resttest.exceptions.EntityAlreadyExistsException;
import com.astontech.resttest.exceptions.EntityNotFoundException;
import com.astontech.resttest.exceptions.FieldNotFoundException;
import com.astontech.resttest.repositories.VehicleRepo;
import com.astontech.resttest.services.VehicleService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo vehicleRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public Vehicle findVehicleByVehicleVIN(String vin) {
        return vehicleRepo.findByVehicleVIN(vin)
                .orElseThrow(() -> new EntityNotFoundException(vin));
    }

    @Override
    @Cacheable(value = "vehicleCache", key = "#id")
    public Vehicle findVehicleByVehicleVINOrId(String vehicleVIN, Integer id) {
        return vehicleRepo.findVehicleByVehicleVINOrId(vehicleVIN, id)
                .orElseThrow(() -> new EntityNotFoundException((vehicleVIN == null ? String.valueOf(id) : vehicleVIN)));
    }



    @Override
    @Cacheable("vehicleCache")
    public Iterable<Vehicle> findAll() {
        return vehicleRepo.findAll();
    }

    @Override
    @CacheEvict(value = "vehicleCache")
    public Vehicle saveVehicle(Vehicle vehicle) {
        String vin = vehicle.getVehicleVIN();
        try {
            return vehicleRepo.save(vehicle);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException(vin);
        }
    }

    @Override
    @CacheEvict(value = "vehicleCache")
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key = "#id")
    public Vehicle patchVehicle(Map<String, Object> updates, Integer id) throws FieldNotFoundException {
        Vehicle vehicleToPatch = vehicleRepo.findVehicleByVehicleVINOrId(null, id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        updates.forEach((k, o) -> {
            System.out.println(k + ":" + o);
            try {
                Field nameField = vehicleToPatch.getClass().getDeclaredField(k);
                nameField.setAccessible(true);
                nameField.set(vehicleToPatch, o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new FieldNotFoundException(k);
            }
        });
        return vehicleRepo.save(vehicleToPatch);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key = "#vehicle.id")
    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepo.delete(vehicle);
    }

    @Override
    @CacheEvict(value = "vehicleCache", key = "#id")
    public void deleteVehicle(Integer id) {
        vehicleRepo.deleteById(id);
    }
}
