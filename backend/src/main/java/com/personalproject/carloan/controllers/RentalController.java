package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.RentalDTO;
import com.personalproject.carloan.dtos.ShowRentalToUser;
import com.personalproject.carloan.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ShowRentalToUser> findById(@PathVariable Long id){
        ShowRentalToUser dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/all/current-date")
    public ResponseEntity<Page<ShowRentalToUser>> findAllFinishToday(Pageable pageable){
        return ResponseEntity.ok(service.findAllFinishToday(pageable));
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

    @PutMapping(value = "/{rentalId}/finish")
    public ResponseEntity<String> finishRental(@PathVariable Long rentalId){
        RentalDTO dto = service.finishRental(rentalId);
        return ResponseEntity.ok("Success! Rental " + dto.getId() + " finished and vehicle " + dto.getVehicle().getVehicleName() + " with id " + dto.getVehicle().getId() + " already changed!");
    }

    @PostMapping(value = "/new/rental/to/{userId}/vehicle/{vehicleId}/create")
    public ResponseEntity<ShowRentalToUser> createRentalToOtherUser(@PathVariable Long vehicleId, @PathVariable Long userId, @RequestBody RentalDTO rentalDto){
        ShowRentalToUser rentalResponse = null;

        rentalDto = service.createNewRentalToOtherUser(vehicleId, userId, rentalDto);

        rentalResponse = new ShowRentalToUser(rentalDto);

        return ResponseEntity.ok().body(rentalResponse);
    }
}
