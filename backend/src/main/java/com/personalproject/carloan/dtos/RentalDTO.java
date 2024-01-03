package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Rental;

import java.time.Instant;

public class RentalDTO {

    private Long id;
    private Instant checkin;
    private Instant checkout;

    public RentalDTO(){}

    public RentalDTO(Long id, Instant checkin, Instant checkout) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public RentalDTO(Rental entity) {
        id = entity.getId();
        checkin = entity.getCheckin();
        checkout = entity.getCheckout();
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
}
