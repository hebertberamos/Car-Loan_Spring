package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Notification;

import java.time.Instant;

public class NotificationDTO {

    private Long id;
    private String notificationText;
    private Instant moment;
    private boolean readed;
//    private Long userId;

    public NotificationDTO() {}

    public NotificationDTO(Long id, String notificationText, Instant moment, boolean readed/*, Long userId*/) {
        this.id = id;
        this.notificationText = notificationText;
        this.moment = moment;
        this.readed = readed;
//        this.userId = userId;
    }

    public NotificationDTO(Notification entity){
        id = entity.getId();
        notificationText = entity.getText();
        moment = entity.getMoment();
        readed = entity.isRead();
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

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
}
