package com.personalproject.carloan.entities;

import com.personalproject.carloan.entities.enums.StatusVehicle;
import com.personalproject.carloan.entities.enums.TypeVehicle;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_Vehicle")
    private String typeVehicle;
    private String vehicleName;
    private String brand;
    private String plate;
    private Integer yearManufacture;
    @Enumerated(EnumType.STRING)
    private StatusVehicle statusVehicle;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double pricePerHour;
    private Double pricePerDay;


    public Vehicle(){
    }

    public Vehicle(Long id, String typeVehicle, String vehicleName, String brand, String plate, Integer yearManufacture, StatusVehicle statusVehicle, String description) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.description = description;

        switch (this.statusVehicle){
            case VIP : {
                pricePerHour = 120.0;
                pricePerDay = 500.0;
                break;
            }
            case POPULAR : {
                pricePerHour = 70.0;
                pricePerDay = 300.0;
                break;
            }
            case ANTIQUITY : {
                pricePerHour = 250.0;
                pricePerDay = 1000.0;
                break;
            }
            default :
                throw new IllegalArgumentException("Vehicle status not identify");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
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
