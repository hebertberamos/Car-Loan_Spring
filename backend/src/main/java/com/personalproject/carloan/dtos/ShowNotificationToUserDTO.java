package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.Notification;

import java.time.Instant;

public class ShowNotificationToUserDTO {

    private String text;
    private Instant moment;

    public ShowNotificationToUserDTO() {}

    public ShowNotificationToUserDTO(String text, Instant moment) {
        this.text = text;
        this.moment = moment;
    }

    public ShowNotificationToUserDTO(Notification entity){
        text = entity.getText();
        moment = entity.getMoment();
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
}
