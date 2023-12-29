package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.enums.StatusVehicle;

public class CarDTO {

    private Long id;
    private String name;
    private String brand;
    private String plate;
    private Integer yearManufacture;
    private StatusVehicle statusVehicle;
    private  Integer numberOfDoors;

    public CarDTO(){
    }

    public CarDTO(Long id, String name, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, Integer numberOfDoors) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.numberOfDoors = numberOfDoors;
    }

    public CarDTO(Car car){
        id = car.getId();
        name = car.getName();
        brand = car.getBrand();
        plate = car.getPlate();
        yearManufacture = car.getYearManufacture();
        statusVehicle = car.getStatusVehicle();
        numberOfDoors = car.getNumberOfDoors();
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

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
