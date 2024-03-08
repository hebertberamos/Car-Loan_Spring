package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.services.VehicleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAll(Pageable pageable){
        Page<VehicleDTO> PageVehicles = service.findAll(pageable);
        return ResponseEntity.ok().body(PageVehicles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        VehicleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody VehicleDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/insert/motorcycle")
    public ResponseEntity<MotorcycleDTO> insertMotorcycle(@RequestBody MotorcycleDTO dto){
        dto = service.createMotorcycle(dto);
        return ResponseEntity.ok().build();
    }
}
