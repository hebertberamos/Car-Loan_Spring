package com.personalproject.carloan.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_review")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reviewText;
    private Integer quantityStars;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public Review() {}

    public Review(Long id, String reviewText, Integer quantityStars, Instant moment, User author, Vehicle vehicle) {
        this.id = id;
        this.reviewText = reviewText;
        this.quantityStars = quantityStars;
        this.moment = moment;
        this.author = author;
        this.vehicle = vehicle;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
