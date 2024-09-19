package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jshell.Snippet;

@Entity
@Table(name = "tb_motorcycle")
public class Motorcycle extends Vehicle{

    private boolean hasFairing;

    public Motorcycle() {}

    public Motorcycle(Long id, String img, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, boolean hasFairing, Rental rental) {
        super(id, img, name, brand, plate, manufactureYear, status, description, available, rating, rental);


        if(status.equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(250.0);
            this.setPricePerDay(1200.0);
        } else if(status.equals(StatusVehicle.POPULAR)) {
            this.setPricePerHour(50.0);
            this.setPricePerDay(150.0);
        } else if(status.equals(StatusVehicle.VIP)){
            this.setPricePerHour(230.0);
            this.setPricePerDay(1000.0);
        }

        this.hasFairing = hasFairing;
    }

    public Motorcycle(String img, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, boolean hasFairing, Rental rental) {
        super(img, name, brand, plate, manufactureYear, status, description, available, rating, rental);

        if(status.equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(250.0);
            this.setPricePerDay(1200.0);
        } else if(status.equals(StatusVehicle.POPULAR)) {
            this.setPricePerHour(50.0);
            this.setPricePerDay(150.0);
        } else if(status.equals(StatusVehicle.VIP)){
            this.setPricePerHour(230.0);
            this.setPricePerDay(1000.0);
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
