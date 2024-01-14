package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;


//    @GetMapping
//    public ResponseEntity<List<VehicleDTO>> findAll(){
//        List<VehicleDTO> dtos = service.findAll();
//        return  ResponseEntity.ok().body(dtos);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        VehicleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<VehicleDTO>> findAllCars(@RequestParam("type_Vehicle")String typeVehicle){
        List<VehicleDTO> cars = service.findAllVehicleTypeCar(typeVehicle);
        return ResponseEntity.ok().body(cars);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id,@RequestBody VehicleDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        VehicleDTO dto = service.delete(id);
        if(dto != null){
            return  ResponseEntity.ok("Elemento com id " + id + " deletado com sucesso!");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> insert(@Valid @RequestBody VehicleDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
}
