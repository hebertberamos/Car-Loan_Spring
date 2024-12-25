package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ShowRentalToVehicle {
    private boolean running;
    private Double rentalValue;
    private Long vehicle_id;
    private boolean isCurrently;
    private LocalDateTime checkinLocalDateTime;
    private LocalDateTime checkoutLocalDateTime;
    private List<LocalDate> datesBetweenCheckinAndCheckout = new ArrayList<>();

    public ShowRentalToVehicle() {}

    public ShowRentalToVehicle(Instant checkin, Instant checkout) {

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime checkinLocalDateTime = LocalDateTime.ofInstant(checkin, zoneId);
        LocalDateTime checkoutLocalDateTime = LocalDateTime.ofInstant(checkout, zoneId);

        this.checkinLocalDateTime = checkinLocalDateTime;
        this.checkoutLocalDateTime = checkoutLocalDateTime;
    }

    public ShowRentalToVehicle(Rental entity){

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime checkinLocalDateTime = LocalDateTime.ofInstant(entity.getCheckin(), zoneId);
        LocalDateTime checkoutLocalDateTime = LocalDateTime.ofInstant(entity.getCheckout(), zoneId);

        this.checkinLocalDateTime = checkinLocalDateTime;
        this.checkoutLocalDateTime = checkoutLocalDateTime;
        running = entity.isRunning();
        rentalValue = entity.getRentalValue();
        vehicle_id = entity.getRentedVehicle().getId();
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

    public Long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public LocalDateTime getCheckinLocalDateTime() {
        return checkinLocalDateTime;
    }

    public void setCheckinLocalDateTime(LocalDateTime checkinLocalDateTime) {
        this.checkinLocalDateTime = checkinLocalDateTime;
    }

    public LocalDateTime getCheckoutLocalDateTime() {
        return checkoutLocalDateTime;
    }

    public void setCheckoutLocalDateTime(LocalDateTime checkoutLocalDateTime) {
        this.checkoutLocalDateTime = checkoutLocalDateTime;
    }

    public List<LocalDate> getDatesBetweenCheckinAndCheckout() {
        return datesBetweenCheckinAndCheckout;
    }

    public boolean isCurrently() {
        return isCurrently;
    }

    public void setCurrently(boolean currently) {
        isCurrently = currently;
    }
}
