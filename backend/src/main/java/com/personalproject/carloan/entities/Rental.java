package com.personalproject.carloan.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant checkin;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant checkout;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant refundMoment;
    private boolean running;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
    private Deliver deliver;

    @OneToOne(mappedBy = "rental", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle rentedVehicle;

    public Rental(){}

    public Rental(Long id, Instant checkin, Instant checkout, Instant refundMoment, boolean running, User user, Deliver deliver, Payment payment, Vehicle rentedVehicle) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;

        if(Instant.now().isBefore(checkout)){
            running = true;
        }
        else {
            running = false;
        }

        this.user = user;
        this.deliver = deliver;
        this.payment = payment;
        this.rentedVehicle = rentedVehicle;
    }

    public Rental(Instant checkin, Instant checkout, Instant refundMoment, boolean running, User user, Deliver deliver, Payment payment, Vehicle rentedVehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;
        this.running = running;

        if(Instant.now().isBefore(checkout)){
            running = true;
        }
        else {
            running = false;
        }

        this.user = user;
        this.deliver = deliver;
        this.payment = payment;
        this.rentedVehicle = rentedVehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getRefundMoment() {
        return refundMoment;
    }

    public void setRefundMoment(Instant refundMoment) {
        this.refundMoment = refundMoment;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
