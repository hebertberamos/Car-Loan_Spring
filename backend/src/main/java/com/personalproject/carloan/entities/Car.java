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
    
    public Car (String img, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, Integer numberOfDoors, Double trunkSpace, boolean hasStep, Rental rental){
        super(img, name, brand, plate, manufactureYear, status, description, available, rating, rental);
        switch(status){
            case ANTIQUITY:
                this.setPricePerHour(350.0);
                this.setPricePerDay(1500.0);
                break;
            case POPULAR:
                this.setPricePerHour(80.0);
                this.setPricePerDay(200.0);
                break;
            case VIP:
                this.setPricePerHour(300.0);
                this.setPricePerDay(1200.0);
                break;
        }
        this.numberOfDoors = numberOfDoors;
        this.trunkSpace = trunkSpace;
        this.hasStep = hasStep;
    }

    public Car(Long id, String img, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, boolean available, Double rating, Integer numberOfDoors, Double trunkSpace, boolean hasStep, Rental rental) {
        super(id, img, name, brand, plate, manufactureYear, status, description, available, rating, rental);

        switch(status){
            case ANTIQUITY:
                setPricePerHour(350.0);
                setPricePerDay(1500.0);
                break;
            case POPULAR:
                setPricePerHour(80.0);
                setPricePerDay(200.0);
                break;
            case VIP:
                setPricePerHour(300.0);
                setPricePerDay(1200.0);
                break;
        }

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
