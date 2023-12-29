package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;

public class Motorcycle extends Vehicle{

    private Boolean hasFairing;

    public Motorcycle(){
    }

    public Motorcycle(Long id, String name, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, Boolean hasFairing) {
        super(id, name, brand, plate, yearManufacture, statusVehicle);
        this.hasFairing = hasFairing;
    }

    public Boolean getHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(Boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
