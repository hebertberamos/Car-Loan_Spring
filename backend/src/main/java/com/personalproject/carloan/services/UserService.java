package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.dtos.UserUpdateDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public UserDTO findById(Long id){
        authenticationService.validateSelfOrAdmin(id); //  Verify if user is searching for your id or is a ADMIN

        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserUpdateDTO dto){
        User user = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        repository.save(user);
        return new UserDTO(user);
    }
}
