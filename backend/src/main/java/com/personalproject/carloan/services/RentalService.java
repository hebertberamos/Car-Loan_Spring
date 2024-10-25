package com.personalproject.carloan.services;

import com.personalproject.carloan.calculators.RentalCalculator;
import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RentalCalculator rentalCalculator;

    public Rental createRental(RentalDTO rentalDto, Vehicle vehicle, User user) throws Exception {
        double rentalAmount = rentalCalculator.calculateRentalValue(rentalDto.getCheckin(), rentalDto.getCheckout(), vehicle.getPricePerHour(), vehicle.getPricePerDay());

        checkMoment(rentalDto.getCheckin());
        checkMoment(rentalDto.getCheckout());

        if(rentalDto.getCheckin().isBefore(rentalDto.getCheckout())){
            Rental rental = new Rental();
            rental.setCheckin(rentalDto.getCheckin());
            rental.setCheckout(rentalDto.getCheckout());
            rental.setRefundMoment(null);
            rental.setRunning(true);
            rental.setUser(user);
            rental.setRentedVehicle(vehicle);
            rental.setRentalValue(rentalAmount);

            return rental;
        }

        throw new Exception("Checkin never can be after checkout");
    }

    // Buscando pelo id
    @Transactional(readOnly = true)
    public RentalDTO findById(Long id){
        Optional<Rental> optional = repository.findById(id);
        Rental rental = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new RentalDTO(rental);
    }

    @Transactional
    public RentalDTO update(Long id, RentalDTO dto){
        Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

            rental.setId(dto.getId());
            rental.setCheckin(dto.getCheckin());
            rental.setCheckout(dto.getCheckout());

        repository.save(rental);
        return dto = new RentalDTO(rental);
    }

    @Transactional
    public RentalDTO delete(Long id){
        try {
            Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(rental);

            return new RentalDTO(rental);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    private static boolean checkMoment(Instant moment) throws Exception {
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(18, 0);

        LocalDate momentDate = moment.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate actualDate = LocalDate.now();

        LocalTime time = moment.atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime timeNow = LocalTime.now();

        // Verify if the rental date is after now
        if(momentDate.isAfter(actualDate)){
            if(time.isBefore(start) || time.isAfter(end)){
                throw new Exception("This time can not be resolved");
            }
            return true;
        // Verify if the rental is today, so need to verify if the hour is after the current hour.
        } else if(momentDate.isEqual(actualDate)){
            if(time.isBefore(start) || time.isAfter(end) || time.isBefore(timeNow)){
                throw new Exception("This time can not be resolved");
            }
            return true;
        } else {
            throw new Exception("This time can not be resolved");
        }
    }
}
