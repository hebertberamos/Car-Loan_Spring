package com.personalproject.carloan.dtos;

import com.personalproject.carloan.entities.enums.UserRole;

public record RegisterUserDTO(String firstName, String lastName, String email, String password, String cpf, Integer age, UserRole role) {
}
