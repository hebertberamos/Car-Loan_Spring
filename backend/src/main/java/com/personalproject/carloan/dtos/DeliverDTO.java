package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Deliver;
import com.personalproject.carloan.entities.Rental;

import java.time.Instant;

public class DeliverDTO {

    private Long id;
    private Instant deliverMoment;
    private Long rentalid;

    public DeliverDTO() {}

    public DeliverDTO(Long id, Instant deliverMoment, Long rentalid) {
        this.deliverMoment = deliverMoment;
        this.rentalid = rentalid;
    }

    public DeliverDTO(Deliver entity) {
        deliverMoment = entity.getDeliverMoment();
        rentalid = entity.getRental().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDeliverMoment() {
        return deliverMoment;
    }

    public void setDeliverMoment(Instant deliverMoment) {
        this.deliverMoment = deliverMoment;
    }

    public Long getRentalid() {
        return rentalid;
    }

    public void setRentalid(Long rentalid) {
        this.rentalid = rentalid;
    }
}
