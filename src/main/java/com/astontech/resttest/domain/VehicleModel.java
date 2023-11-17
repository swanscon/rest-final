package com.astontech.resttest.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "vehicleModel", uniqueConstraints = {@UniqueConstraint(columnNames = "vehicleModelName")})
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String vehicleModelName;

    private String vehicleModelImage;

    @ManyToOne
    @JoinColumn(name = "vehicleMakeId", referencedColumnName = "id")
    private VehicleMake vehicleMake;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public String getVehicleModelImage() {
        return vehicleModelImage;
    }

    public void setVehicleModelImage(String vehicleModelImage) {
        this.vehicleModelImage = vehicleModelImage;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public VehicleModel() {}

    public VehicleModel(String vehicleModelName, String vehicleModelImage, VehicleMake vehicleMake) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleModelImage = vehicleModelImage;
        this.vehicleMake = vehicleMake;
    }
}
