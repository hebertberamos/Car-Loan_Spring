package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.entities.enums.StatusVehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VehicleDTO {

    private Long id;
    private String name;
    private String brand;
    private String plate;
    private Integer manufactureYear;
    private StatusVehicle status;
    private String description;
    private Double pricePerHour;
    private Double pricePerDay;
    private boolean available;
    private Double rating;
    private List<ReviewDTO> reviews = new ArrayList<>();
    private Set<RentalDTO> rentalsDone = new HashSet<>();

    public VehicleDTO() {}

    public VehicleDTO(Long id, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.status = status;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.available = available;
        this.rating = rating;
    }

    public VehicleDTO(Vehicle entity) {
        name = entity.getName();
        brand = entity.getBrand();
        plate = entity.getBrand();
        manufactureYear = entity.getManufactureYear();
        status = entity.getStatus();
        description = entity.getDescription();
        pricePerHour = entity.getPricePerDay();
        pricePerHour = entity.getPricePerDay();
        available = entity.isAvailable();
        rating = entity.getRating();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                this.reviews.add(new ReviewDTO(r));
            }
        }

        if(entity.getRentalsDone() != null){
            for(Rental r : entity.getRentalsDone()){
                this.rentalsDone.add(new RentalDTO(r));
            }
        }
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

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public StatusVehicle getStatus() {
        return status;
    }

    public void setStatus(StatusVehicle status) {
        this.status = status;
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
        pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public Set<RentalDTO> getRentalsDone() {
        return rentalsDone;
    }
}
