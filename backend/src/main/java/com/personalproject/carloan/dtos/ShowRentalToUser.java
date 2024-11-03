package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

public class ShowRentalToUser {

    private Instant checkin;
    private Instant checkout;
    private Double rentalValue;
    private ShowUserToRental user;
    private ShowVehicleToRental vehicle;

    private UserMapper mapper;

    public ShowRentalToUser(UserMapper mapper) {
        this.mapper = mapper;
    }

    public ShowRentalToUser() {}

    public ShowRentalToUser(Instant checkin, Instant checkout, Double rentalValue, ShowUserToRental user, Vehicle vehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.rentalValue = rentalValue;
        this.user = user;
        this.vehicle = new ShowVehicleToRental(vehicle);
    }

    public ShowRentalToUser(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        rentalValue = entity.getRentalValue();
        user = new ShowUserToRental(entity.getUser());
        vehicle = new ShowVehicleToRental(entity.getRentedVehicle());
    }

    public Instant getCheckin() {
        return checkin;
    }

    public void setCheckin(Instant checkin) {
        this.checkin = checkin;
    }

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public Double getRentalValue() {
        return rentalValue;
    }

    public void setRentalValue(Double rentalValue) {
        this.rentalValue = rentalValue;
    }

    public ShowUserToRental getUser() {
        return user;
    }

    public void setUser(ShowUserToRental user) {
        this.user = user;
    }

    public ShowVehicleToRental getVehicle() {
        return vehicle;
    }

    public void setVehicle(ShowVehicleToRental vehicle) {
        this.vehicle = vehicle;
    }
}
