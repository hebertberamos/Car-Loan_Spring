package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Review;

import java.io.Serializable;
import java.time.Instant;

public class ReviewDTO implements Serializable {

    private Long id;
    private String text;
    private Integer quantityStars;
    private Instant moment;
    private Long authorId;
    private Long vehicleId;
    private Long rentalId;

    public ReviewDTO() {}

    public ReviewDTO(Long id, String text, Integer quantityStars, Instant moment, Long authorId, Long vehicleId, Long rentalId) {
        this.id = id;
        this.text = text;
        this.quantityStars = quantityStars;
        this.moment = moment;
        this.authorId = authorId;
        this.vehicleId = vehicleId;
        this.rentalId = rentalId;
    }

    public ReviewDTO (Review entity) {
        id = entity.getId();
        text = entity.getText();
        quantityStars = entity.getQuantityStars();
        moment = entity.getMoment();
        authorId  = entity.getAuthor().getId();
        vehicleId = entity.getVehicle().getId();
        rentalId = entity.getRental().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getQuantityStars() {
        return quantityStars;
    }

    public void setQuantityStars(Integer quantityStars) {
        this.quantityStars = quantityStars;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
}
