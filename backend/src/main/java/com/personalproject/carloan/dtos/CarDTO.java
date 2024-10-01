package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.enums.StatusVehicle;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {

    private Long id;
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

    private Integer numberOfDoors;
    private Double trunkSpace;
    private boolean hasStep;


    public CarDTO() {}

    public CarDTO(String img, String vehicleName, String brand, String plate, Integer manufactureYear, StatusVehicle vehicleStatus, String vehicleDescription, boolean available, Double rating, ShowRentalToVehicle rental, List<ShowReviewToVehicle> reviews,Integer numberOfDoors, Double trunkSpace, boolean hasStep) {
        this.img = img;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.vehicleStatus = vehicleStatus;
        this.vehicleDescription = vehicleDescription;

        if(vehicleStatus.equals(StatusVehicle.VIP)){
            this.setPricePerHour(300.0);
            this.setPricePerDay(1200.0);
        } else if(vehicleStatus.equals(StatusVehicle.POPULAR)){
            this.setPricePerHour(80.0);
            this.setPricePerDay(200.0);
        } else if(vehicleStatus.equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(350.0);
            this.setPricePerDay(1500.0);
        }

        this.available = available;
        this.rating = rating;
        this.rental = rental;
        this.reviews = reviews;
        this.numberOfDoors = numberOfDoors;
        this.trunkSpace = trunkSpace;
        this.hasStep = hasStep;
    }

    public CarDTO(Car entity){
        img = entity.getImg();
        vehicleName = entity.getName();
        brand = entity.getBrand();
        plate = entity.getPlate();
        manufactureYear = entity.getManufactureYear();
        vehicleStatus = entity.getStatus();
        vehicleDescription = entity.getDescription();

        if(vehicleStatus.equals(StatusVehicle.VIP)){
            this.setPricePerHour(300.0);
            this.setPricePerDay(1200.0);
        } else if(vehicleStatus.equals(StatusVehicle.POPULAR)){
            this.setPricePerHour(80.0);
            this.setPricePerDay(200.0);
        } else if(vehicleStatus.equals(StatusVehicle.ANTIQUITY)){
            this.setPricePerHour(350.0);
            this.setPricePerDay(1500.0);
        }

        available = entity.isAvailable();
        rating = entity.getRating();
        numberOfDoors = entity.getNumberOfDoors();
        trunkSpace = entity.getTrunkSpace();
        hasStep = entity.isHasStep();

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

    public List<ShowReviewToVehicle> getReviews() {
        return reviews;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Double getTrunkSpace() {
        return trunkSpace;
    }

    public void setTrunkSpace(Double trunkSpace) {
        this.trunkSpace = trunkSpace;
    }

    public boolean isHasStep() {
        return hasStep;
    }

    public void setHasStep(boolean hasStep) {
        this.hasStep = hasStep;
    }
}
