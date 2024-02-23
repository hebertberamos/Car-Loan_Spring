package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.enums.UserRole;
import jakarta.validation.constraints.*;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "Email inválido. Informe um váido, por favor.")
    private String email;
    @NotNull
    @Min(value = 18, message = "O usuário deve ter pelo menos 18 anos")
    private Integer age;
    private UserRole role;

    public UserDTO(){
    }

    public UserDTO(Long id, String name, String email, String cpf, Integer age, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        age = user.getAge();
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
}
