package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_vehicle")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String img;
    private String vehicleName;
    private String brand;
    private String plate;
    private Integer manufactureYear;
    private StatusVehicle vehicleStatus;
    @Column(columnDefinition = "TEXT")
    private String vehicleDescription;
    private Double pricePerHour;
    private Double pricePerDay;
    private boolean available;
    private Double rating;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "rentedVehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Rental rental;

    public Vehicle() {}

    public Vehicle(Long vehicleId, String img, String vehicleName, String brand, String plate, Integer manufactureYear, StatusVehicle vehicleStatus, String vehicleDescription, boolean available, Double rating, Rental rental) {
        this.vehicleId = vehicleId;
        this.img = img;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.vehicleStatus = vehicleStatus;
        this.vehicleDescription = vehicleDescription;
        this.available = available;
        this.rating = rating;
        this.rental = rental;
    }

    public Vehicle(String img, String vehicleName, String brand, String plate, Integer manufactureYear, StatusVehicle vehicleStatus, String vehicleDescription,  boolean available, Double rating, Rental rental) {
        this.vehicleName = vehicleName;
        this.img = img;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.vehicleStatus = vehicleStatus;
        this.vehicleDescription = vehicleDescription;
        this.available = available;
        this.rating = rating;
        this.rental = rental;
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

    public String getName() {
        return vehicleName;
    }

    public void setName(String vehicleName) {
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

    public StatusVehicle getStatus() {
        return vehicleStatus;
    }

    public void setStatus(StatusVehicle vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public String getDescription() {
        return vehicleDescription;
    }

    public void setDescription(String vehicleDescription) {
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

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleId, vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }
}
