package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.PaymentDTO;
import com.personalproject.carloan.entities.Payment;
import com.personalproject.carloan.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> findAll(){
        List<PaymentDTO> dtos = service.findAll();
        return ResponseEntity.ok().body(dtos);
    }

}
