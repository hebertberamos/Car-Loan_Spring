package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.services.VehicleService;
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


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody VehicleDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/insert/motorcycle")
    public ResponseEntity<MotorcycleDTO> insertMotorcycle(@RequestBody MotorcycleDTO dto){
        dto = service.createMotorcycle(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/insert/car")
    public ResponseEntity<CarDTO> insertCar(@RequestBody CarDTO dto){
        dto = service.createCar(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/rental/create/{id}")
    public ResponseEntity<RentalDTO> createRentalByVehicle(@PathVariable Long id, @RequestBody RentalDTO rentalDto){
        rentalDto = service.createRentalByVehicle(id, rentalDto);
        return ResponseEntity.ok().body(rentalDto);
    }
}
