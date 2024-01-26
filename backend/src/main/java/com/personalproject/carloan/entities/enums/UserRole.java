package com.personalproject.carloan.entities.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    EMPLOYEE("employee");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}