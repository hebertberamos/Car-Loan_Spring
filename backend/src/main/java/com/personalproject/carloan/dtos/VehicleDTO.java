package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class VehicleDTO {

    private String vehicleName;
    private String brand;
    private String plate;
    private Integer yearManufacture;
    private StatusVehicle statusVehicle;
    private String description;
    private Double pricePerHour;
    private Double pricePerDay;

    public VehicleDTO(){
    }

    public VehicleDTO(String vehicleName, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, String description, Double pricePerHour, Double pricePerDay) {
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    public VehicleDTO(Vehicle vehicle){
        vehicleName = vehicle.getVehicleName();
        brand = vehicle.getBrand();
        plate = vehicle.getPlate();
        yearManufacture = vehicle.getYearManufacture();
        statusVehicle = vehicle.getStatusVehicle();
        description = vehicle.getDescription();
        pricePerHour = vehicle.getPricePerHour();
        pricePerDay = vehicle.getPricePerDay();
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Integer getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(Integer yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    public StatusVehicle getStatusVehicle() {
        return statusVehicle;
    }

    public void setStatusVehicle(StatusVehicle statusVehicle) {
        this.statusVehicle = statusVehicle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
