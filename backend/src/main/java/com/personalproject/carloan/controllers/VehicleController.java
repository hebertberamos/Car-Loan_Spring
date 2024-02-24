package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAll(Pageable pageable){
        Page<VehicleDTO> PageVehicles = service.findAll(pageable);
        return ResponseEntity.ok().body(PageVehicles);
    }
}
