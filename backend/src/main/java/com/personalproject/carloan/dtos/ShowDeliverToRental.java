package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Deliver;

import java.time.Instant;

public class ShowDeliverToRental {

    private Instant deliverMoment;

    public ShowDeliverToRental() {}

    public ShowDeliverToRental(Instant deliverMoment) {
        this.deliverMoment = deliverMoment;
    }

    public ShowDeliverToRental(Deliver entity){
        deliverMoment = entity.getDeliverMoment();
    }

    public Instant getDeliverMoment() {
        return deliverMoment;
    }

    public void setDeliverMoment(Instant deliverMoment) {
        this.deliverMoment = deliverMoment;
    }
}
