package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

public class RentalDTO implements Serializable {

    private Long id;
    @NotNull
    @FutureOrPresent(message = "A data de aluguel não pode ser instanciada com uma data passada")
    private Instant checkin;
    @NotNull
    @FutureOrPresent (message = "A data de devolução não pode ser instanciada com uma data passada")
    private Instant checkout;
    private Instant refundMoment;
    private boolean running;
    private Double rentalValue;
    private UserDTO user;
    private VehicleDTO vehicle;

    public RentalDTO(){}

    // =>  Create a Rental informing the values of the attributes.
    public RentalDTO(Instant checkin, Instant checkout, Instant refundMoment, boolean running, Double rentalValue, UserDTO user, VehicleDTO vehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;
        this.running = running;
        this.rentalValue = rentalValue;
        this.user = user;
        this.vehicle = vehicle;
    }

    public RentalDTO(Rental entity) {
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        refundMoment = entity.getRefundMoment();
        running = entity.isRunning();
        rentalValue = entity.getRentalValue();
        user = new UserDTO(entity.getUser());
        vehicle = new VehicleDTO(entity.getRentedVehicle());
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
