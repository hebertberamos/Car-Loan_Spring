package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public List<UserDTO> findAll(){
        List<User> users = repository.findAll();
        return users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional
    public UserDTO findById(Long id){
        Optional<User> optional = repository.findById(id);
        User user = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new UserDTO(user);
    }

    // Atualizar
    @Transactional
    public UserDTO update(Long id, UserDTO dto){
        User user = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCpf(dto.getCpf());
        user.setAge(dto.getAge());

        repository.save(user);
        return dto = new UserDTO(user);
    }

    // Deletar
    @Transactional
    public UserDTO delete(Long id){
        try {
            User user = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(user);

            return new UserDTO(user);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
    public UserDTO insert(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCpf(dto.getCpf());
        user.setAge(dto.getAge());

        repository.save(user);
        dto = new UserDTO(user);
        return dto;
    }


}
