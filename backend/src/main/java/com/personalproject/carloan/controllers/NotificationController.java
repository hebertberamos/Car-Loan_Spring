package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.NotificationDTO;
import com.personalproject.carloan.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> findByUser(){
        List<NotificationDTO> dtos = service.findByUser();
        return ResponseEntity.ok().body(dtos);
    }
}
