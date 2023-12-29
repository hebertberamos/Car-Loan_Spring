package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class MotorcycleDTO {

    private Long id;
    private String name;
    private String brand;
    private String plate;
    private Integer yearManufacture;
    private StatusVehicle statusVehicle;
    private Boolean hasFairing;

    public MotorcycleDTO(){
    }

    public MotorcycleDTO(Long id, String name, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, Boolean hasFairing) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.hasFairing = hasFairing;
    }

    public MotorcycleDTO(Motorcycle motorcycle){
        id = motorcycle.getId();
        name = motorcycle.getName();
        brand = motorcycle.getBrand();
        plate = motorcycle.getPlate();
        yearManufacture = motorcycle.getYearManufacture();
        statusVehicle = motorcycle.getStatusVehicle();
        hasFairing = motorcycle.getHasFairing();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(Boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
