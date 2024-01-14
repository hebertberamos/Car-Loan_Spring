package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VehicleDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    @Pattern(regexp = "CAR|MOTORCYCLE", message = "Os tipos de veículos aceitos são (CAR e MOTORCYCLE)")
    private String typeVehicle;
    @NotBlank(message = "Campo obrigatório")
    private String vehicleName;
    @NotBlank(message = "Campo obrigatório")
    private String brand;
    @NotBlank(message = "Campo obrigatório")
    @Pattern(regexp = "^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$", message = "Formao de placa inválido. Utilize o padrão (TTTNTNN)")
    private String plate;
    @NotNull(message = "Campo obrigatório")
    private Integer yearManufacture;
    @NotBlank
    @Pattern(regexp = "VIP|POPULAR|ANTIQUITY", message = "Os tipos de veículos aceitos são (VIP, POPULAR e ANTIQUITY)")
    private String statusVehicle;
    @NotBlank(message = "Campo obrigatório")
    private String description;
    private Double pricePerHour;
    private Double pricePerDay;

    public VehicleDTO(){
    }

    public VehicleDTO(Long id, String typeVehicle, String vehicleName, String brand, String plate, Integer yearManufacture, String statusVehicle, String description, Double pricePerHour, Double pricePerDay) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.description = description;
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
    }

    public VehicleDTO(Long id, String typeVehicle, String vehicleName, String brand, String plate, Integer yearManufacture, String statusVehicle, String description) {
        this.id = id;
        this.typeVehicle = typeVehicle;
        this.vehicleName = vehicleName;
        this.brand = brand;
        this.plate = plate;
        this.yearManufacture = yearManufacture;
        this.statusVehicle = statusVehicle;
        this.description = description;
    }

    public VehicleDTO(Vehicle vehicle){
        id = vehicle.getId();
        typeVehicle = vehicle.getTypeVehicle();
        vehicleName = vehicle.getVehicleName();
        brand = vehicle.getBrand();
        plate = vehicle.getPlate();
        yearManufacture = vehicle.getYearManufacture();
        statusVehicle = vehicle.getStatusVehicle();
        description = vehicle.getDescription();
        pricePerHour = vehicle.getPricePerHour();
        pricePerDay = vehicle.getPricePerDay();
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

    public String getStatusVehicle() {
        return statusVehicle;
    }

    public void setStatusVehicle(String statusVehicle) {
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
}
