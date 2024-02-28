package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Vehicle;

import javax.swing.text.html.parser.Entity;

public class ShowVehicleToRental {

    private String name;
    private String brand;
    private String plate;
    private Double rating;

    public ShowVehicleToRental() {}

    public ShowVehicleToRental(Vehicle entity) {
        name = entity.getName();
        brand = entity.getBrand();
        plate = entity.getPlate();
        rating = entity.getRating();
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
