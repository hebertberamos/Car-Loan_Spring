package com.personalproject.carloan.controllers;

import com.personalproject.carloan.dtos.RentalDTO;
import com.personalproject.carloan.dtos.ShowRentalToUser;
import com.personalproject.carloan.services.RentalService;
import com.personalproject.carloan.services.exceptions.OutOfWorkingHoursException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping(value = "/all")
    public ResponseEntity<Page<ShowRentalToUser>> findAllFinishToday(
            @RequestParam(value = "finishTodayOnly", defaultValue = "false") Boolean finishTodayOnly,
            @RequestParam(value = "alreadyFinishedOnly", defaultValue = "false") Boolean alreadyFinishedOnly,
            @RequestParam(value = "runningOnly", defaultValue = "false") Boolean runningOnly,
            @RequestParam(required = false, value = "checkinDate", defaultValue = "") String checkinDate,
            @RequestParam(required = false, value = "endDate", defaultValue = "") String endDate,
            Pageable pageable){

        LocalDate localDateCheckin = null;
        LocalDate localDateEndDate = null;

        if(!checkinDate.isEmpty() && endDate.isEmpty()){
            localDateCheckin = LocalDate.parse(checkinDate);
        }

        if(!checkinDate.isEmpty() && !endDate.isEmpty()){
            localDateCheckin = LocalDate.parse(checkinDate);
            localDateEndDate = LocalDate.parse(endDate);
        }

        if(checkinDate.isEmpty() && !endDate.isEmpty()){
            throw new OutOfWorkingHoursException("DEV: error when trying to add the endDate and not the checkinDate in rental's method findAll. (need to create a message to show to the user here)");
        }

        return ResponseEntity.ok(service.findRentals(finishTodayOnly, alreadyFinishedOnly, runningOnly, localDateCheckin, localDateEndDate, pageable));
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
