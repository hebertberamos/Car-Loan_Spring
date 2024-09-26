package com.personalproject.carloan.services.validations;

import com.personalproject.carloan.controllers.exceptions.FieldMessage;
import com.personalproject.carloan.dtos.UserUpdateDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.AuthenticationService;
import com.personalproject.carloan.services.exceptions.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!uriVars.isEmpty()){
            // Capture the id from the URI
            long userId = Long.parseLong(uriVars.get("id"));

            return listofErrors(dto, context, userId);
        } else if(userDetails != null){
            try {
                // Capture user from token
                String emailFromToken = userDetails.getUsername();
                User userFromToken = repository.findByEmail(emailFromToken);
                long userId = userFromToken.getId();

                // Check if the user is the self
                authenticationService.validateSelf(userId);

                return listofErrors(dto, context, userId);
            } catch(ForbiddenException e){
                System.out.println("CODING ERRO!" + e.getMessage());
            }
        }

        return false;
    }

    private boolean listofErrors(UserUpdateDTO dto, ConstraintValidatorContext context, long userId){
        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if(user != null && userId != user.getId()){
            list.add(new FieldMessage("Email", "O Email digitado encontra-se já registrado em nossa base de dados. Verifique seu email e tente novamente."));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
