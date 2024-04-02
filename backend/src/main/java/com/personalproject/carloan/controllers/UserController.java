package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.dtos.UserUpdateDTO;
import com.personalproject.carloan.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> dtos = service.findAll();
        return  ResponseEntity.ok().body(dtos);
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        UserDTO userDTO = service.delete(id);
        if(userDTO != null){
            return  ResponseEntity.ok("Elemento com id " + id + " deletado com sucesso!");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createNewLogin(@Valid @RequestBody UserInsertDTO dto){
        UserDTO newDto = service.createNewLogin(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }
}
