package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.services.RentalService;
import com.personalproject.carloan.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAll(
            @RequestParam(value = "availableOnly", defaultValue = "false") Boolean availableOnly,
            @RequestParam(value = "brand", defaultValue = "") String brand,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "vehicleType", defaultValue = "0") Integer vehicleType,
            Pageable pageable){
        Page<VehicleDTO> pageVehicles = service.findAll(availableOnly, brand, name, vehicleType, pageable);
        return ResponseEntity.ok().body(pageVehicles);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        VehicleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/insert/motorcycle")
    public ResponseEntity<MotorcycleDTO> insertMotorcycle(@RequestBody MotorcycleDTO dto){
        dto = service.createMotorcycle(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/insert/car")
    public ResponseEntity<CarDTO> insertCar(@RequestBody CarDTO dto){
        CarDTO carDto = service.createCar(dto);
        return ResponseEntity.ok().body(carDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/car/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarDTO dto){
        dto = service.updateCar(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/update/motorcycle/{id}")
    public ResponseEntity<MotorcycleDTO> updateMotorcycle(@PathVariable Long id, @Valid @RequestBody MotorcycleDTO dto) {
        dto = service.updateMotorcycle(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/{id}/create/rental")
    public ResponseEntity<RentalDTO> createRentalByVehicle(@PathVariable Long id, @RequestBody RentalDTO rentalDto){
        rentalDto = service.createRentalByVehicle(id, rentalDto);
        return ResponseEntity.ok().body(rentalDto);
    }

    @PostMapping(value = "/{id}/create/review")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDto){
        reviewDto = service.newReviewToVehicle(id, reviewDto);

        return ResponseEntity.ok().body(reviewDto);

    }
}
