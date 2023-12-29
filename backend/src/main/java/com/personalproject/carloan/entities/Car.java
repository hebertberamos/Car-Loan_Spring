package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle{

    private Integer numberOfDoors;

    public Car(){
    }

    public Car(Long id, String name, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, Integer numberOfDoors) {
        super(id, name, brand, plate, yearManufacture, statusVehicle);
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

}
