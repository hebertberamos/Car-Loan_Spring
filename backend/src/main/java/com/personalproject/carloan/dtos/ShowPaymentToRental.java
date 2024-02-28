package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Payment;

import java.time.Instant;

public class ShowPaymentToRental {

    private Instant paymentMoment;
    private Double paymentAmount;

    public ShowPaymentToRental() {}

    public  ShowPaymentToRental(Payment entity){
        paymentAmount = entity.getPaymentAmount();
        paymentMoment = entity.getPaymentMoment();
    }

    public Instant getPaymentMoment() {
        return paymentMoment;
    }

    public void setPaymentMoment(Instant paymentMoment) {
        this.paymentMoment = paymentMoment;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
