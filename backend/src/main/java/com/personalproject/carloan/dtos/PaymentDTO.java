package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Payment;

import java.time.Instant;

public class PaymentDTO {

    private Long id;
    private boolean payed;
    private Instant paymentMoment;
    private Double paymentAmount;
    private RentalDTO rentalDto;
    private Long payerId;

    public PaymentDTO() {}

    public PaymentDTO(Long id, boolean payed, Instant paymentMoment, Double paymentAmount, RentalDTO rentalDto, Long payerId) {
        this.id = id;
        this.payed = payed;
        this.paymentMoment = paymentMoment;
        this.paymentAmount = paymentAmount;
        this.rentalDto = rentalDto;
        this.payerId = payerId;
    }

    public PaymentDTO (Payment entity){
        id = entity.getId();
        payed = entity.isPayed();
        paymentMoment = entity.getPaymentMoment();
        paymentAmount = entity.getPaymentAmount();
        rentalDto = new RentalDTO(entity.getRental());
        payerId = entity.getPayer().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
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

    public RentalDTO getRentalDto() {
        return rentalDto;
    }

    public void setRentalDto(RentalDTO rentalDto) {
        this.rentalDto = rentalDto;
    }

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }
}
