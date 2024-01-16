package com.personalproject.carloan.services.validations;

import com.personalproject.carloan.controllers.exceptions.FieldMessage;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        //configuração para saber se já existe algum endereço de email igual ao que o usuário está tentando inserir
        User userEmail = repository.findByEmail(dto.getEmail());

        if(userEmail != null) {
            list.add(new FieldMessage("Email", "Email inválido! Este endereço de email já existe em nossa tabela de dados, por favor, verifique seu email"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty(); // Se a lista estiver vazia vai dar certo a inserção do novo usuário, se não, vai exibir ao usuário uma resposta indicando o erro.
    }
}
