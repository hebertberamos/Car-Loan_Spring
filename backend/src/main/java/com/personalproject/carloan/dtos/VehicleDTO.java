package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class VehicleDTO {

    private Long id;
    private String name;
    private String brand;
    private String plate;
    private Integer yearManufacture;
    private StatusVehicle statusVehicle;

    public VehicleDTO(){
    }

    public VehicleDTO(Long id, String name, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
    }

    public VehicleDTO(Vehicle vehicle){
        id = vehicle.getId();
        name = vehicle.getName();
        brand = vehicle.getBrand();
        plate = vehicle.getPlate();
        yearManufacture = vehicle.getYearManufacture();
        statusVehicle = vehicle.getStatusVehicle();
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
}
