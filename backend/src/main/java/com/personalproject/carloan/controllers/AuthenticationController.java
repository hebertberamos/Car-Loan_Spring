package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.LoginUserDTO;
import com.personalproject.carloan.dtos.RegisterUserDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body("token = " + token);
    }

    @PostMapping(value = "/register")
    public ResponseEntity login(@RequestBody @Valid RegisterUserDTO dto){
        if(repository.findByEmail(dto.email()) != null) return ResponseEntity.badRequest().build();

        String encriptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User(dto.firstName(), dto.lastName(), dto.email(), encriptedPassword, dto.cpf(), dto.age(), dto.role());

        repository.save(user);
        return ResponseEntity.ok().build();
    }
}
