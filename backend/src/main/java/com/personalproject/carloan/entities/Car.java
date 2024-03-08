package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_car")
public class Car extends Vehicle{

    private Integer numberOfDoors;
    private Double trunkSpace;
    private boolean hasStep;

    public Car() {}
    
    public Car (String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating, Integer numberOfDoors, Double trunkSpace, boolean hasStep, Rental rental){
        super(name, brand, plate, manufactureYear, status, description, pricePerHour, pricePerDay, available, rating, rental);
        this.numberOfDoors = numberOfDoors;
        this.trunkSpace = trunkSpace;
        this.hasStep = hasStep;
    }

    public Car(Long id, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating, Integer numberOfDoors, Double trunkSpace, boolean hasStep, Rental rental) {
        super(id, name, brand, plate, manufactureYear, status, description, pricePerHour, pricePerDay, available, rating, rental);
        this.numberOfDoors = numberOfDoors;
        this.trunkSpace = trunkSpace;
        this.hasStep = hasStep;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Double getTrunkSpace() {
        return trunkSpace;
    }

    public void setTrunkSpace(Double trunkSpace) {
        trunkSpace = trunkSpace;
    }

    public boolean isHasStep() {
        return hasStep;
    }

    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }
}
