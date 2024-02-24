package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Payment;
import com.personalproject.carloan.entities.enums.PaymentStatus;

import java.time.Instant;

public class PaymentDTO {

    private Long id;
    private PaymentStatus status;
    private Instant paymentMoment;
    private Double paymentAmount;
    private Long rentalId;
    private Long payerId;

    public PaymentDTO() {}

    public PaymentDTO(Long id, PaymentStatus status, Instant paymentMoment, Double paymentAmount, Long rentalId, Long payerId) {
        this.id = id;
        this.status = status;
        this.paymentMoment = paymentMoment;
        this.paymentAmount = paymentAmount;
        this.rentalId = rentalId;
        this.payerId = payerId;
    }

    public PaymentDTO (Payment entity){
        id = entity.getId();
        status = entity.getStatus();
        paymentMoment = entity.getPaymentMoment();
        paymentAmount = entity.getPaymentAmount();
        rentalId = entity.getRental().getId();
        payerId = entity.getPayer().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }
}
