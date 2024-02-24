package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.*;
import com.personalproject.carloan.entities.enums.UserRole;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    private List<Long> rentalsId = new ArrayList<>();
    private List<Long> paymentsId = new ArrayList<>();
    private List<Long> reviewsId = new ArrayList<>();


    public UserDTO(){
    }

    public UserDTO(Long id, String name, String email, String cpf, Integer age, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.age = age;
        this.role = role;
    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        cpf = user.getCpf();
        age = user.getAge();
        role = user.getRole();

        for(Notification n : user.getNotifications()){
            notifications.add(new NotificationDTO(n));
        }

        for(Payment p : user.getPayments()){
            this.paymentsId.add(p.getId());
        }

        for(Review r : user.getReviews()){
            this.reviewsId.add(r.getId());
        }


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

    public List<Long> getRentalsId() {
        return rentalsId;
    }

    public List<Long> getPaymentsId() {
        return paymentsId;
    }

    public List<Long> getReviewsId() {
        return reviewsId;
    }
}
