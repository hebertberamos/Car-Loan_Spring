package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.Vehicle;

import java.time.Instant;

public class ShowRentalToUser {

    private Instant checkin;
    private Instant checkout;
    private ShowVehicleToRental vehicle;

    public ShowRentalToUser() {}

    public ShowRentalToUser(Instant checkin, Instant checkout, Vehicle vehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.vehicle = new ShowVehicleToRental(vehicle);
    }

    public ShowRentalToUser(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
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

    public ShowVehicleToRental getVehicle() {
        return vehicle;
    }

    public void setVehicle(ShowVehicleToRental vehicle) {
        this.vehicle = vehicle;
    }
}
