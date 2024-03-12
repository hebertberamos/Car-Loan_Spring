package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;

import java.time.Instant;

public class CreateRentalDTO {

    private Long id;
    private Instant checkin;
    private Instant checkout;
    private Instant refundMoment;
    private boolean running;
    private DeliverDTO deliver;
    private PaymentDTO payment;
    private UserDTO user;
    private VehicleDTO vehicle;
    private Long vehicleId;

    public CreateRentalDTO() {}

    public CreateRentalDTO(Instant checkin, Instant checkout, Instant refundMoment, DeliverDTO deliver, PaymentDTO payment, UserDTO user, Long vehicleId) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.refundMoment = refundMoment;

        if(Instant.now().isBefore(checkout)){
            running = false;
        }
        else {
            running = true;
        }

        this.deliver = deliver;
        this.payment = payment;
        this.user = user;
        this.vehicleId = vehicleId;
    }

    public CreateRentalDTO(Rental entity){
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
        refundMoment = entity.getRefundMoment();
        running = entity.isRunning();
        deliver = new DeliverDTO(entity.getDeliver());
        payment = new PaymentDTO(entity.getPayment());
        user = new UserDTO(entity.getUser());
        vehicle = new VehicleDTO(entity.getRentedVehicle());
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

    public DeliverDTO getDeliver() {
        return deliver;
    }

    public void setDeliver(DeliverDTO deliver) {
        this.deliver = deliver;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
