package com.personalproject.carloan.dtos;

import com.personalproject.carloan.services.validations.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "O campo de senha é obrigatório")
    private String password;
    @NotBlank
    @CPF
    private String cpf;

    public UserInsertDTO(){
        super();
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
}
