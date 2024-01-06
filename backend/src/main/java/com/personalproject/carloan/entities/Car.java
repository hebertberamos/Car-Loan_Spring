package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_car")
public class Car extends Vehicle{

    private Integer numberOfDoors;

//    @OneToOne(mappedBy = "car")
//    private Rental rental;

    public Car(){
    }

    public Car(Long id, String vehicleName, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, String description, Integer numberOfDoors) {
        super(id, vehicleName, brand, plate, yearManufacture, statusVehicle, description);
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
