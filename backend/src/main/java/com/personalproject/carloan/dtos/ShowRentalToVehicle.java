package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Payment;
import com.personalproject.carloan.entities.Rental;

import java.time.Instant;

public class ShowRentalToVehicle {

    private Instant checkin;
    private Instant checkout;
    private ShowPaymentToRental payment;

    public ShowRentalToVehicle() {}

    public ShowRentalToVehicle(Instant checkin, Instant checkout, ShowPaymentToRental payment) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.payment = payment;
    }

    public ShowRentalToVehicle(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        payment = new ShowPaymentToRental(entity.getPayment());
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
}
