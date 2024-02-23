//package com.personalproject.carloan.dtos;
//
//import com.personalproject.carloan.entities.Rental;
//import com.personalproject.carloan.entities.User;
//import com.personalproject.carloan.entities.Vehicle;
//import jakarta.validation.constraints.FutureOrPresent;
//import jakarta.validation.constraints.NotNull;
//
//import java.time.Instant;
//
//public class RentalDTO {
//
//    private Long id;
//    @NotNull
//    @FutureOrPresent(message = "A data de aluguel não pode ser instanciada com uma data passada")
//    private Instant checkin;
//    @NotNull
//    @FutureOrPresent (message = "A data de devolução não pode ser instanciada com uma data passada")
//    private Instant checkout;
//    @NotNull(message = "Obrigatória a instanciação de um usuário para o aluguel")
//    private User user;
//    @NotNull(message = "Obrigatória a instanciação de um veículo para o aluguel")
//    private Vehicle vehicle;
//
//    public RentalDTO(){}
//
//    public RentalDTO(Long id, Instant checkin, Instant checkout, User user, Vehicle vehicle) {
//        this.id = id;
//        this.checkin = checkin;
//        this.checkout = checkout;
//        this.user = user;
//        this.vehicle = vehicle;
//    }
//
//    public RentalDTO(Rental entity) {
//        id = entity.getId();
//        checkin = entity.getCheckin();
//        checkout = entity.getCheckout();
//        user = entity.getUser();
//        vehicle = entity.getVehicle();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Instant getCheckin() {
//        return checkin;
//    }
//
//    public void setCheckin(Instant checkin) {
//        this.checkin = checkin;
//    }
//
//    public Instant getCheckout() {
//        return checkout;
//    }
//
//    public void setCheckout(Instant checkout) {
//        this.checkout = checkout;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//}
