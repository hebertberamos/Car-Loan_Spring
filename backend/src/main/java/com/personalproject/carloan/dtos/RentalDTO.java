package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    private Long deliverId;
    private Long paymentId;
    @NotNull(message = "Obrigatória a instanciação de um usuário para o aluguel")
    private Long userId;

   private List<ReviewDTO> reviews = new ArrayList<>();

    public RentalDTO(){}

    public RentalDTO(Long id, Instant checkin, Instant checkout, Instant refundMoment, boolean running, Long deliverId,  Long paymentId,Long userId) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;
        this.running = running;
        this.deliverId = deliverId;
        this.paymentId = paymentId;
        this.userId = userId;
    }

    public RentalDTO(Rental entity) {
        id = entity.getId();
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        refundMoment = entity.getRefundMoment();
        running = entity.isRunning();
        deliverId = entity.getDeliver().getId();

        if(entity.getPayment() != null){
            paymentId = entity.getPayment().getId();
        }

        userId = entity.getUser().getId();
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

    public Long getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }
}
