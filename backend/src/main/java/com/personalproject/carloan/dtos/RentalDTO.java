package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

public class RentalDTO implements Serializable {

    private Long id;
    @NotNull
    @FutureOrPresent(message = "A data de aluguel não pode ser instanciada com uma data passada")
    private Instant checkin;
    @NotNull
    @FutureOrPresent (message = "A data de devolução não pode ser instanciada com uma data passada")
    private Instant checkout;
    private Instant refundMoment;
    private boolean running;
    private ShowDeliverToRental deliver;
    private ShowPaymentToRental payment;
    @NotNull(message = "Obrigatória a instanciação de um usuário para o aluguel")
    private ShowUserToReview user;
    private ShowVehicleToRental vehicle;

    public RentalDTO(){}

    public RentalDTO(Instant checkin, Instant checkout, Instant refundMoment, boolean running, Deliver deliver, Payment payment, User user, Vehicle vehicle) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;
        this.running = running;
        this.payment = new ShowPaymentToRental(payment);
        this.deliver = new ShowDeliverToRental(deliver);
        this.user = new ShowUserToReview(user);
        this.vehicle = new ShowVehicleToRental(vehicle);
    }

    public RentalDTO(Rental entity) {
        id = entity.getId();
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        refundMoment = entity.getRefundMoment();
        running = entity.isRunning();
        deliver = new ShowDeliverToRental(entity.getDeliver());

        if(entity.getPayment() != null){
            payment = new ShowPaymentToRental(entity.getPayment());
        }

        user = new ShowUserToReview(entity.getUser());
        vehicle = new ShowVehicleToRental(entity.getRentedVehicle());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCheckin() {
        return checkin;
    }

    public void setCheckin(Instant checkin) {
        this.checkin = checkin;
    }

    public Instant getCheckout() {
        return checkout;
    }

    public void setCheckout(Instant checkout) {
        this.checkout = checkout;
    }

    public Instant getRefundMoment() {
        return refundMoment;
    }

    public void setRefundMoment(Instant refundMoment) {
        this.refundMoment = refundMoment;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ShowDeliverToRental getDeliverId() {
        return deliver;
    }

    public void setDeliverId(ShowDeliverToRental deliverId) {
        this.deliver = deliver;
    }

    public ShowPaymentToRental getPaymentId() {
        return payment;
    }

    public void setPaymentId(ShowPaymentToRental payment) {
        this.payment = payment;
    }

    public ShowUserToReview getUserId() {
        return user;
    }

    public void setUserId(ShowUserToReview userName) {
        this.user = user;
    }

    public ShowVehicleToRental getVehicle() {
        return vehicle;
    }

    public void setVehicle(ShowVehicleToRental vehicle) {
        this.vehicle = vehicle;
    }
}
