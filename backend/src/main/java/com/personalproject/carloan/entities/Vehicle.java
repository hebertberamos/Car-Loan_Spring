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
    private Long id;
    private String name;
    private String brand;
    private String plate;
    private Integer manufactureYear;
    private StatusVehicle status;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double pricePerHour;
    private Double PricePerDay;
    private boolean available;
    private Double rating;

    @OneToMany(mappedBy = "vehicle")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "rentedVehicles")
    private Set<Rental> rentalsDone = new HashSet<>();

    public Vehicle() {}

    public Vehicle(Long id, String name, String brand, String plate, Integer manufactureYear, StatusVehicle status, String description, Double pricePerHour, Double pricePerDay, boolean available, Double rating) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.plate = plate;
        this.manufactureYear = manufactureYear;
        this.status = status;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.PricePerDay = pricePerDay;
        this.available = available;
        this.rating = rating;
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
        return PricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        PricePerDay = pricePerDay;
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

    public Set<Rental> getRentalsDone() {
        return rentalsDone;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
