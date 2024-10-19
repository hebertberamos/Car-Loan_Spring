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
    private Double rentalValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicle_id")
    private Vehicle rentedVehicle;

    public Rental(){}

    public Rental(Long id, Instant checkin, Instant checkout, Instant refundMoment, boolean running, Double rentalValue, User user, Vehicle rentedVehicle) {
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

        this.rentalValue = rentalValue;
        this.user = user;
        this.rentedVehicle = rentedVehicle;
    }

    public Rental(Instant checkin, Instant checkout, Instant refundMoment, boolean running, Double rentalValue, User user, Vehicle rentedVehicle) {
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

        this.rentalValue = rentalValue;
        this.user = user;
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


    public Double getRentalValue() {
        return rentalValue;
    }

    public void setRentalValue(Double rentalValue) {
        this.rentalValue = rentalValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
