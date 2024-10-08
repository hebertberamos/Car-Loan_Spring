package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.RentalDTO;
import com.personalproject.carloan.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<RentalDTO> findById(@PathVariable Long id){
        RentalDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RentalDTO> update(@PathVariable Long id,@RequestBody RentalDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        RentalDTO dto = service.delete(id);
        if(dto != null){
            return  ResponseEntity.ok("Elemento com id " + id + " deletado com sucesso!");
        }
        return ResponseEntity.noContent().build();
    }
}
