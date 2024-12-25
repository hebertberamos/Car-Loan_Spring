package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import com.personalproject.carloan.entities.enums.StatusVehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleDTO {

    private Long vehicleId;
    private String img;
    private String vehicleName;
    private String brand;
    private String plate;
    private Integer manufactureYear;
    private StatusVehicle vehicleStatus;
    private String vehicleDescription;
    private Double pricePerHour;
    private Double pricePerDay;
    private boolean available;
    private Double rating;
    private ShowRentalToVehicle currentRental;
    private final List<ShowRentalToVehicle> scheduledRentals = new ArrayList<>();
    private final List<ShowReviewToVehicle> reviews = new ArrayList<>();

    public VehicleDTO() {}

    // Constructor to create a new Vehicle by an entity
    public VehicleDTO(Vehicle entity) {
        vehicleId = entity.getId();
        img = entity.getImg();
        vehicleName = entity.getName();
        brand = entity.getBrand();
        plate = entity.getPlate();
        manufactureYear = entity.getManufactureYear();
        vehicleStatus = entity.getStatus();
        vehicleDescription = entity.getDescription();
        pricePerHour = entity.getPricePerHour();
        pricePerDay = entity.getPricePerDay();
        available = entity.isAvailable();
        rating = entity.getRating();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                reviews.add(new ShowReviewToVehicle(r));
            }
        }
//        if(entity.getCurrentRental() != null) {
//            if (!entity.isAvailable() && entity.getCurrentRental().isRunning()) {
//                currentRental = new ShowRentalToVehicle(entity.getCurrentRental());
//            }
//        }

        List<Rental> rentals = entity.getScheduledRentals();
        if(!rentals.isEmpty()){
            for(Rental r : entity.getScheduledRentals()){
                scheduledRentals.add(new ShowRentalToVehicle(r));

                if(r.isRunning()){
                    currentRental = new ShowRentalToVehicle(r);
                }
            }
        }
    }

    public VehicleDTO(Car entity){
        this.vehicleId = entity.getId();
        this.img = entity.getImg();
        this.vehicleName = entity.getName();
        this.brand = entity.getBrand();
        this.vehicleStatus = entity.getStatus();
        this.available = entity.isAvailable();
        this.rating = entity.getRating();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                reviews.add(new ShowReviewToVehicle(r));
            }
        }
//        if(entity.getCurrentRental() != null) {
//            if (!entity.isAvailable() &&  entity.getCurrentRental().isRunning()) {
//                currentRental = new ShowRentalToVehicle(entity.getCurrentRental());
//            }
//        }

        if(entity.getScheduledRentals() != null){
            for(Rental r : entity.getScheduledRentals()){
                scheduledRentals.add(new ShowRentalToVehicle(r));
            }
        }
    }

    public VehicleDTO(Motorcycle entity){
        this.vehicleId = entity.getId();
        this.img = entity.getImg();
        this.vehicleName = entity.getName();
        this.brand = entity.getBrand();
        this.vehicleStatus = entity.getStatus();
        this.available = entity.isAvailable();
        this.rating = entity.getRating();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                reviews.add(new ShowReviewToVehicle(r));
            }
        }
//        if(entity.getCurrentRental() != null) {
//            if (!entity.isAvailable() &&  entity.getCurrentRental().isRunning()) {
//                currentRental = new ShowRentalToVehicle(entity.getCurrentRental());
//            }
//        }

        if(entity.getScheduledRentals() != null){
            for(Rental r : entity.getScheduledRentals()){
                scheduledRentals.add(new ShowRentalToVehicle(r));
            }
        }
    }

    // =>  Constructor to use in find all method
//    public VehicleDTO(Long vehicleId, String img, String vehicleName, String brand, StatusVehicle vehicleStatus, boolean available, Double rating, Double pricePerHour, Double pricePerDay) {
//        this.vehicleId = vehicleId;
//        this.img = img;
//        this.vehicleName = vehicleName;
//        this.brand = brand;
//        this.vehicleStatus = vehicleStatus;
//        this.available = available;
//        this.rating = rating;
//        this.pricePerHour = pricePerHour;
//        this.pricePerDay = pricePerDay;
//    }

//    public VehicleDTO(String img, String vehicleName, String brand, String plate, Integer manufactureYear, String vehicleDescription, boolean available, Double rating/*, ShowRentalToVehicle rental*/, List<ShowReviewToVehicle> reviews) {
//        this.img = img;
//        this.vehicleName = vehicleName;
//        this.brand = brand;
//        this.plate = plate;
//        this.manufactureYear = manufactureYear;
//        this.vehicleDescription = vehicleDescription;
//        this.available = available;
//        this.rating = rating;
////        this.currentRental = rental;
//
//        for(ShowReviewToVehicle rev : reviews){
//            reviews.add(rev);
//        }
//    }

    public Long getId() {
        return vehicleId;
    }

    public void setId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public StatusVehicle getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(StatusVehicle vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
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

    public ShowRentalToVehicle getCurrentRental() {
        return currentRental;
    }

    public void setCurrentRental(ShowRentalToVehicle currentRental) {
        this.currentRental = currentRental;
    }

    public List<ShowReviewToVehicle> getReviews() {
        return reviews;
    }

    public List<ShowRentalToVehicle> getScheduledRentals(){return scheduledRentals;}
}
