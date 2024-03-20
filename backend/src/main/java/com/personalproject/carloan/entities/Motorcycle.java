package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_motorcycle")
public class Motorcycle extends Vehicle{

    private boolean hasFairing;

    public Motorcycle() {}

    public Motorcycle(Long id, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, boolean hasFairing, Rental rental) {
        super(id, name, brand, plate, manufactureYear, status, description, available, rating, rental);

        switch(status){
            case ANTIQUITY:
                setPricePerHour(250.0);
                setPricePerDay(1200.0);
                break;
            case POPULAR:
                setPricePerHour(50.0);
                setPricePerDay(150.0);
                break;
            case VIP:
                setPricePerHour(230.0);
                setPricePerDay(1000.0);
                break;
        }

        this.hasFairing = hasFairing;
    }

    public Motorcycle(String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, boolean hasFairing, Rental rental) {
        super(name, brand, plate, manufactureYear, status, description, available, rating, rental);

        switch(status){
            case ANTIQUITY:
                setPricePerHour(250.0);
                setPricePerDay(1200.0);
                break;
            case POPULAR:
                setPricePerHour(50.0);
                setPricePerDay(150.0);
                break;
            case VIP:
                setPricePerHour(230.0);
                setPricePerDay(1000.0);
                break;
        }

        this.hasFairing = hasFairing;
    }

    public boolean isHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
