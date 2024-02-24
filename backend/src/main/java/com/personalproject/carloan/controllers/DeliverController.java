package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.DeliverDTO;
import com.personalproject.carloan.services.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("deliveries")
public class DeliverController {

    @Autowired
    private DeliverService service;

    @GetMapping
    public ResponseEntity<List<DeliverDTO>> findAll(){
        List<DeliverDTO> dtos = service.findAll();
        return ResponseEntity.ok().body(dtos);
    }
}
