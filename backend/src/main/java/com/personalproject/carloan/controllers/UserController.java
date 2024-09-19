package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.dtos.UserUpdateDTO;
import com.personalproject.carloan.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> loadAllUsers(){
        List<UserDTO> users = service.loadAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
     public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

   @PutMapping(value = "/{id}")
   public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto){
        UserDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
   }

   @PostMapping(value = "/new/user")
   public ResponseEntity createNewUser(@RequestBody @Valid UserInsertDTO dto){
        dto = service.createNewUser(dto);

        if(dto != null){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
   }
}
