package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;

import java.time.Instant;

public class ShowRentalToVehicle {

    private Instant checkin;
    private Instant checkout;
    private boolean running;

    public ShowRentalToVehicle() {}

    public ShowRentalToVehicle(Instant checkin, Instant checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public ShowRentalToVehicle(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        running = entity.isRunning();
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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
