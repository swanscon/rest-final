package com.astontech.resttest.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "vehicleMake", uniqueConstraints = {@UniqueConstraint(columnNames = "vehicleMakeName")})
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "make_id")
    private Integer id;

    @Column(nullable = false)
    private String vehicleMakeName;

    @OneToMany

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleMakeName() {
        return vehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }

    public VehicleMake() {}

    public VehicleMake(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }
}
