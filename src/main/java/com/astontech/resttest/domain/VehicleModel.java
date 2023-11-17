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

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "vehicleMakeId")
//    private VehicleMake vehicleMake;

    private Integer vehicleMakeId;

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

    public Integer getVehicleMakeId() {
        return vehicleMakeId;
    }

    public void setVehicleMakeId(Integer vehicleMakeId) {
        this.vehicleMakeId = vehicleMakeId;
    }

    public VehicleModel() {}

    public VehicleModel(String vehicleModelName, Integer vehicleMakeId) {
        this.vehicleModelName = vehicleModelName;
        this.vehicleMakeId = vehicleMakeId;
    }
}
