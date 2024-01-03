package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_motorcycle")
public class Motorcycle extends Vehicle{

    private Boolean hasFairing;

    public Motorcycle(){
    }

    public Motorcycle(Long id, String vehicleName, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, String description, Boolean hasFairing) {
        super(id, vehicleName, brand, plate, yearManufacture, statusVehicle, description);
        this.hasFairing = hasFairing;
    }

    public Boolean getHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(Boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
