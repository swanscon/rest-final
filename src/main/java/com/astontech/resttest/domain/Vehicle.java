package com.astontech.resttest.domain;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "vehicle", uniqueConstraints = {@UniqueConstraint(columnNames = "vehicleVIN")})
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String vehicleVIN;

    private String vehicleLicense;
    private String vehicleYear;
    private String vehicleColor;

    @ManyToOne
    @JoinColumn(name = "vehicleModelId", referencedColumnName = "id")
    private VehicleModel vehicleModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public void setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Vehicle() {}

    public Vehicle(String vehicleVIN, String vehicleLicense, String vehicleYear, String vehicleColor, VehicleModel vehicleModel) {
        this.vehicleVIN = vehicleVIN;
        this.vehicleLicense = vehicleLicense;
        this.vehicleYear = vehicleYear;
        this.vehicleColor = vehicleColor;
        this.vehicleModel = vehicleModel;
    }
}
