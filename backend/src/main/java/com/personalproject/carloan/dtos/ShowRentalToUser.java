package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Payment;
import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.Vehicle;

import java.time.Instant;

public class ShowRentalToUser {

    private Instant checkin;
    private Instant checkout;
    private ShowPaymentToRental payment;
    private ShowVehicleToRental vehicle;

    public ShowRentalToUser() {}

    public ShowRentalToUser(Instant checkin, Instant checkout, Payment payment, Vehicle vehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.payment = new ShowPaymentToRental(payment);
        this.vehicle = new ShowVehicleToRental(vehicle);
    }

    public ShowRentalToUser(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        payment = new ShowPaymentToRental(entity.getPayment());
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

    public ShowPaymentToRental getPayment() {
        return payment;
    }

    public void setPayment(ShowPaymentToRental payment) {
        this.payment = payment;
    }

    public ShowVehicleToRental getVehicle() {
        return vehicle;
    }

    public void setVehicle(ShowVehicleToRental vehicle) {
        this.vehicle = vehicle;
    }
}
