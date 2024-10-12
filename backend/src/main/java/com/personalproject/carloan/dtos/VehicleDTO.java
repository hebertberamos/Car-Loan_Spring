package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.Vehicle;
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
    private ShowRentalToVehicle rental;
    private List<ShowReviewToVehicle> reviews = new ArrayList<>();

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
//        vehicleType = entity.getVehicleType();

        if(entity.getReviews() != null) {
            for(Review r : entity.getReviews()){
                reviews.add(new ShowReviewToVehicle(r));
            }
        }
        if(entity.getRental() != null) {
            if (!entity.isAvailable() && entity.getRental().isRunning()) {
                rental = new ShowRentalToVehicle(entity.getRental());
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
        if(entity.getRental() != null) {
            if (!entity.isAvailable() &&  entity.getRental().isRunning()) {
                rental = new ShowRentalToVehicle(entity.getRental());
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
        if(entity.getRental() != null) {
            if (!entity.isAvailable() &&  entity.getRental().isRunning()) {
                rental = new ShowRentalToVehicle(entity.getRental());
            }
        }
    }

    // =>  Constructor to use in find all method
    public VehicleDTO(Long vehicleId, String img, String vehicleName, String brand, StatusVehicle vehicleStatus, boolean available, Double rating, Double pricePerHour, Double pricePerDay) {
        this.vehicleId = vehicleId;
        this.img = img;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.vehicleStatus = vehicleStatus;
        this.available = available;
        this.rating = rating;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    public VehicleDTO(String img, String vehicleName, String brand, String plate, Integer manufactureYear, String vehicleDescription, boolean available, Double rating, ShowRentalToVehicle rental, List<ShowReviewToVehicle> reviews) {
        this.img = img;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.vehicleDescription = vehicleDescription;
        this.available = available;
        this.rating = rating;
        this.rental = rental;

        for(ShowReviewToVehicle rev : reviews){
            reviews.add(rev);
        }
    }

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

    public ShowRentalToVehicle getRental() {
        return rental;
    }

    public void setRental(ShowRentalToVehicle rental) {
        this.rental = rental;
    }

    public List<ShowReviewToVehicle> getReviews() {
        return reviews;
    }
}
