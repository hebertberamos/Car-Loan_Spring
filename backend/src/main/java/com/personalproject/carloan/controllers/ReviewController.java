package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.entities.Review;
import com.personalproject.carloan.services.ReviewService;
import com.personalproject.carloan.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll(){
        List<ReviewDTO> dtos = service.findAll();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/vehicle/{vehicleId}")
    public ResponseEntity<List<ReviewDTO>> findAllByVehicle(@PathVariable Long vehicleId){
        List<ReviewDTO> reviews = new ArrayList<>();
        try {
            reviews = service.findAllByVehicle(vehicleId);
            return ResponseEntity.ok().body(reviews);
        } catch (DatabaseException e){
            return ResponseEntity.badRequest().body(reviews);
        }
    }
}
