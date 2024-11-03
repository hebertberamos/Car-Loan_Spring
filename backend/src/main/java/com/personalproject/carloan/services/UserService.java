package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.dtos.UserUpdateDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.mappers.UserMapper;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.exceptions.ForbiddenException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import com.personalproject.carloan.services.exceptions.UnauthorizedException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDTO>loadAllUsers(){
        List<User> users = repository.findAll();
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO findById(Long id){
        try {
            authenticationService.validateSelfOrAdmin(id); //  Verify if user is searching for your id or is a ADMIN
        }
        catch(NullPointerException e){
            throw new UnauthorizedException("Error!! You trying to do something you are note able!\nJust ADM can see this\n" + e.getMessage());
        }

        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDTO updateAdmin(Long id, UserUpdateDTO dto){
        User user = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        repository.save(user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDTO personalUpdate (UserUpdateDTO body){
        try{
            User user = authenticationService.authenticated();
            authenticationService.validateSelf(user.getId());

            user.setName(body.getName());
            user.setEmail(body.getEmail());

            repository.save(user);

            return userMapper.toUserDto(user);
        } catch (ForbiddenException e){
            System.out.println("Error!\nTrying to verify the user. " + e.getMessage());
        }

        return null;
    }

    public UserInsertDTO createNewUser(UserInsertDTO dto){
        if(repository.findByEmail(dto.getEmail()) != null) return null;

        String encriptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), encriptedPassword, dto.getCpf(), dto.getAge(), dto.getRole());

        repository.save(user);

        return userMapper.toUserInsertDto(user);
    }
}
