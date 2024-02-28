package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.User;

public class ShowUserToReview {

    private String name;
    private String email;

    public ShowUserToReview() {}

    public ShowUserToReview(User entity) {
        name = entity.getName();
        email = entity.getEmail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
