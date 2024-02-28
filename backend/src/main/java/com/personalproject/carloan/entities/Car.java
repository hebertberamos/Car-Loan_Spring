package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_car")
public class Car extends Vehicle{

    private Integer numberOfDoors;
    private Double TrunkSpace;
    private boolean hasStep;

    public Car() {}

    public Car(Long id, String name, String brand, String plate, Integer year, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating, Integer numberOfDoors, Double trunkSpace, boolean hasStep, Rental rental) {
        super(id, name, brand, plate, year, status, description, pricePerHour, pricePerDay, available, rating, rental);
        this.numberOfDoors = numberOfDoors;
        TrunkSpace = trunkSpace;
        this.hasStep = hasStep;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Double getTrunkSpace() {
        return TrunkSpace;
    }

    public void setTrunkSpace(Double trunkSpace) {
        TrunkSpace = trunkSpace;
    }

    public boolean isHasStep() {
        return hasStep;
    }

    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }
}
