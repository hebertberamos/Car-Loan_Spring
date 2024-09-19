package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Review;

import java.io.Serializable;
import java.time.Instant;

public class ReviewDTO implements Serializable {

    private Long id;
    private String reviewText;
    private Integer quantityStars;
    private Instant moment;
    private Long authorId;
    private Long vehicleId;

    public ReviewDTO() {}

    public ReviewDTO(Long id, String reviewText, Integer quantityStars, Instant moment, Long authorId, Long vehicleId) {
        this.id = id;
        this.reviewText = reviewText;
        this.quantityStars = quantityStars;
        this.moment = moment;
        this.authorId = authorId;
        this.vehicleId = vehicleId;
    }

    public ReviewDTO (Review entity) {
        id = entity.getId();
        reviewText = entity.getText();
        quantityStars = entity.getQuantityStars();
        moment = entity.getMoment();
        authorId  = entity.getAuthor().getId();
        vehicleId = entity.getVehicle().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return reviewText;
    }

    public void setText(String reviewText) {
        this.reviewText = reviewText;
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
}
