package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Notification;

import java.time.Instant;

public class NotificationDTO {

    private Long id;
    private String text;
    private Instant moment;
    private boolean read;
    private Long userId;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String text, Instant moment, boolean read, Long userId) {
        this.id = id;
        this.text = text;
        this.moment = moment;
        this.read = read;
        this.userId = userId;
    }

    public NotificationDTO(Notification entity){
        id = entity.getId();
        text = entity.getText();
        moment = entity.getMoment();
        read = entity.isRead();
        userId = entity.getUser().getId();
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

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
