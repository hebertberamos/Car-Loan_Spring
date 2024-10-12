package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class MotorcycleDTO extends VehicleDTO {

    private boolean hasFairing;

    public MotorcycleDTO(){}

    public MotorcycleDTO(Motorcycle entity){
        super(entity);

        if(entity.getStatus().equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(250.0);
            this.setPricePerDay(1100.0);
        } else if(entity.getStatus().equals(StatusVehicle.POPULAR)){
            this.setPricePerHour(50.0);
            this.setPricePerDay(150.0);
        } else if(entity.getStatus().equals(StatusVehicle.VIP)){
            this.setPricePerHour(230.0);
            this.setPricePerDay(900.0);
        }

        hasFairing = entity.isHasFairing();

    }

    public boolean isHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
