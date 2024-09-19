package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import com.personalproject.carloan.entities.enums.UserRole;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "Email inválido. Informe um váido, por favor.")
    private String email;
    private String cpf;
    @NotNull
    @Min(value = 18, message = "O usuário deve ter pelo menos 18 anos")
    private Integer age;
    private UserRole role;

    private List<NotificationDTO> notifications = new ArrayList<>();
    private List<ShowRentalToUser> rentals = new ArrayList<>();
    private List<Long> paymentsId = new ArrayList<>();
    private List<Long> reviewsId = new ArrayList<>();


    public UserDTO(){
    }

    public UserDTO(String name, String email, UserRole role, Integer age) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.age = age;
    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        cpf = user.getCpf();
        age = user.getAge();
        role = user.getRole();

        if(user.getNotifications() != null){
            for(Notification n : user.getNotifications()) {
                notifications.add(new NotificationDTO(n));
            }
        }

//        for(Rental r : user.getRentals()){
//            if(r.isRunning() == true){
//                this.rentals.add(new ShowRentalToUser(r));
//            }
//        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }

    public List<ShowRentalToUser> getRentalsId() {
        return rentals;
    }

    public List<Long> getPaymentsId() {
        return paymentsId;
    }

    public List<Long> getReviewsId() {
        return reviewsId;
    }
}
