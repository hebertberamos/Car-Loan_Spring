package com.personalproject.carloan.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_notification")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notificationText;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private boolean readed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(){}

    public Notification(Long id, String notificationText, Instant moment, boolean readed, User user) {
        this.id = id;
        this.notificationText = notificationText;
        this.moment = moment;
        this.readed = readed;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return notificationText;
    }

    public void setText(String notificationText) {
        this.notificationText = notificationText;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public boolean isRead() {
        return readed;
    }

    public void setRead(boolean readed) {
        this.readed = readed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
