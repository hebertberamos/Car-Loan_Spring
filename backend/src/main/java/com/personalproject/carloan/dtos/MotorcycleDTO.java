package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.enums.StatusVehicle;

import java.util.ArrayList;
import java.util.List;

public class MotorcycleDTO {

    private Long id;
    private String img;
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
    private ShowRentalToVehicle rental;
    private List<ShowReviewToVehicle> reviews = new ArrayList<>();

    private boolean hasFairing;

    public MotorcycleDTO(){}

    public MotorcycleDTO(String img, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating, ShowRentalToVehicle rental, List<ShowReviewToVehicle> reviews, boolean hasFairing) {
        this.img = img;
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
        this.rental = rental;
        this.reviews = reviews;
        this.hasFairing = hasFairing;
    }

    public MotorcycleDTO(Motorcycle entity){
        img = entity.getImg();
        name = entity.getName();
        brand = entity.getBrand();
        plate = entity.getPlate();
        manufactureYear = entity.getManufactureYear();
        status = entity.getStatus();
        description = entity.getDescription();

        switch(entity.getStatus()){
            case ANTIQUITY:
                pricePerHour = 250.0;
                pricePerDay = 1100.0;
                break;
            case POPULAR:
                pricePerHour = 50.0;
                pricePerDay = 150.00;
                break;
            case VIP:
                pricePerHour = 230.0;
                pricePerDay = 900.00;
                break;
        }

        available = entity.isAvailable();
        rating = entity.getRating();
        hasFairing = entity.isHasFairing();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                this.reviews.add(new ShowReviewToVehicle(r));
            }
        }
        if(entity.getRental() != null) {
            if (entity.isAvailable() == false || entity.getRental().isRunning() == true) {
                rental = new ShowRentalToVehicle(entity.getRental());
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        this.pricePerDay = pricePerDay;
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

    public ShowRentalToVehicle getRental() {
        return rental;
    }

    public void setRental(ShowRentalToVehicle rental) {
        this.rental = rental;
    }

    public void setReviews(List<ShowReviewToVehicle> reviews) {
        this.reviews = reviews;
    }

    public boolean isHasFairing() {
        return hasFairing;
    }

    public void setHasFairing(boolean hasFairing) {
        this.hasFairing = hasFairing;
    }
}
