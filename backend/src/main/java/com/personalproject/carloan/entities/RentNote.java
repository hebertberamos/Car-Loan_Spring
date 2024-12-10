package com.personalproject.carloan.entities;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RentNote {

    private String userName;
    private String userEmail;
    private String userCpf;
    private String vehicleName;
    private String vehicleBrand;
    private String vehiclePlate;
    private LocalDateTime rentalCheckin;
    private LocalDateTime rentalCheckout;
    private LocalDateTime refundMoment;
    private Double firstRentalValue;
    private Double lastRentalValue;

    public RentNote(){}

//    public RentNote(String userName, String userEmail, String userCpf, String vehicleName, String vehicleBrand, String vehiclePlate, LocalDateTime rentalCheckin, LocalDateTime rentalCheckout, LocalDateTime refundMoment, Double firstRentalValue, Double lastRentalValue) {
//        this.userName = userName;
//        this.userEmail = userEmail;
//        this.userCpf = userCpf;
//        this.vehicleName = vehicleName;
//        this.vehicleBrand = vehicleBrand;
//        this.vehiclePlate = vehiclePlate;
//        this.rentalCheckin = rentalCheckin;
//        this.rentalCheckout = rentalCheckout;
//        this.refundMoment = refundMoment;
//        this.firstRentalValue = firstRentalValue;
//        this.lastRentalValue = lastRentalValue;
//    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public LocalDateTime getRentalCheckin() {
        return rentalCheckin;
    }

    public void setRentalCheckin(LocalDateTime rentalCheckin) {
        this.rentalCheckin = rentalCheckin;
    }

    public LocalDateTime getRentalCheckout() {
        return rentalCheckout;
    }

    public void setRentalCheckout(LocalDateTime rentalCheckout) {
        this.rentalCheckout = rentalCheckout;
    }

    public LocalDateTime getRefundMoment() {
        return refundMoment;
    }

    public void setRefundMoment(LocalDateTime refundMoment) {
        this.refundMoment = refundMoment;
    }

    public Double getFirstRentalValue() {
        return firstRentalValue;
    }

    public void setFirstRentalValue(Double firstRentalValue) {
        this.firstRentalValue = firstRentalValue;
    }

    public Double getLastRentalValue() {
        return lastRentalValue;
    }

    public void setLastRentalValue(Double lastRentalValue) {
        this.lastRentalValue = lastRentalValue;
    }
}
