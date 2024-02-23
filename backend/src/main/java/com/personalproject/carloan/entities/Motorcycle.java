package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_motorcycle")
public class Motorcycle extends Vehicle{

    private boolean hasFairing;

    public Motorcycle() {}

    public Motorcycle(Long id, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating, boolean hasFairing) {
        super(id, name, brand, plate, manufactureYear, status, description, pricePerHour, pricePerDay, available, rating);
        this.hasFairing = hasFairing;
    }

    public boolean isHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
