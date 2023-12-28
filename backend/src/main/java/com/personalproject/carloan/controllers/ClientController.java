package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.ClientDTO;
import com.personalproject.carloan.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;


    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> dtos = service.findAll();
        return  ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
