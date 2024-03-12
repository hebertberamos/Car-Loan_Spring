package com.personalproject.carloan.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean payed;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant paymentMoment;
    private Double paymentAmount;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    public Payment() {};

    public Payment(Long id, boolean payed, Instant paymentMoment, Double paymentAmount, Rental rental, User payer) {
        this.id = id;
        this.payed = payed;
        this.paymentMoment = paymentMoment;
        this.paymentAmount = paymentAmount;
        this.rental = rental;
        this.payer = payer;
    }

    public Payment(boolean payed, Instant paymentMoment, Double paymentAmount, Rental rental, User payer) {
        this.payed = payed;
        this.paymentMoment = paymentMoment;
        this.paymentAmount = paymentAmount;
        this.rental = rental;
        this.payer = payer;
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

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
