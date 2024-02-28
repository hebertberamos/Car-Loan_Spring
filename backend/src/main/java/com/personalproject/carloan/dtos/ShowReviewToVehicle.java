package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.entities.User;

import java.time.Instant;

public class ShowReviewToVehicle {

    private String text;
    private Integer quantityStars;
    private Instant moment;
    private ShowUserToReview author; // =>  name and email

    public ShowReviewToVehicle() {}

    public ShowReviewToVehicle(Review entity) {
        text = entity.getText();
        quantityStars = entity.getQuantityStars();
        moment = entity.getMoment();
        author = new ShowUserToReview(entity.getAuthor());
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

    public ShowUserToReview getAuthor() {
        return author;
    }

    public void setAuthor(ShowUserToReview author) {
        this.author = author;
    }
}
