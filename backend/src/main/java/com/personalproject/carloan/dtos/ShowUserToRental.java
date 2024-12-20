package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.User;

public class ShowUserToRental {

    private String name;
    private String email;
    private  String cpf;

    public ShowUserToRental(){}

    public ShowUserToRental(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
    }

    public ShowUserToRental(UserDTO dto){
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
