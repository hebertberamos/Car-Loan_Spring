package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class CarDTO extends VehicleDTO{

    private Integer numberOfDoors;
    private Double trunkSpace;
    private boolean hasStep;


    public CarDTO() {}

    public CarDTO(Car entity){
        super(entity);

        if(entity.getStatus().equals(StatusVehicle.VIP)){
            this.setPricePerHour(300.0);
            this.setPricePerDay(1200.0);
        } else if(entity.getStatus().equals(StatusVehicle.POPULAR)){
            this.setPricePerHour(80.0);
            this.setPricePerDay(200.0);
        } else if(entity.getStatus().equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(350.0);
            this.setPricePerDay(1500.0);
        }

        numberOfDoors = entity.getNumberOfDoors();
        trunkSpace = entity.getTrunkSpace();
        hasStep = entity.isHasStep();
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
        this.trunkSpace = trunkSpace;
    }

    public boolean isHasStep() {
        return hasStep;
    }

    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }
}
