package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
