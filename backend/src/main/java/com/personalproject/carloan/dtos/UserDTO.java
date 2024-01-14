package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.User;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email(message = "Email inválido. Informe um váido, por favor.")
    private String email;
    @NotBlank(message = "Este campo deve ser preenchido com algum valor")
    private String password;
    @NotBlank
    @CPF
    private String cpf;
    @NotNull
    @Min(value = 18, message = "O usuário deve ter pelo menos 18 anos")
    private Integer age;

    public UserDTO(){
    }

    public UserDTO(Long id, String firstName, String lastName, String email, String password, String cpf, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.age = age;
    }

    //Construção de um ClientDTO a partir de um objeto Client
    public UserDTO(User user){
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        password = user.getPassword();
        cpf = user.getCpf();
        age = user.getAge();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
