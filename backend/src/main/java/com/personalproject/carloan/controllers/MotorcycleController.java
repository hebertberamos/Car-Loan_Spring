package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.services.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {

    @Autowired
    private MotorcycleService service;


    @GetMapping
    public ResponseEntity<List<MotorcycleDTO>> findAll(){
        List<MotorcycleDTO> dtos = service.findAll();
        return  ResponseEntity.ok().body(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MotorcycleDTO> findById(@PathVariable Long id){
        MotorcycleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MotorcycleDTO> update(@PathVariable Long id,@RequestBody MotorcycleDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        MotorcycleDTO dto = service.delete(id);
        if(dto != null){
            return  ResponseEntity.ok("Elemento com id " + id + " deletado com sucesso!");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<MotorcycleDTO> insert(@RequestBody MotorcycleDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
