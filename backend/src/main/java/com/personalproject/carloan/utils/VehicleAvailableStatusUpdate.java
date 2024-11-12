package com.personalproject.carloan.utils;

import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class VehicleAvailableStatusUpdate {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private VehicleRepository vehicleRepository;


    @Scheduled(cron = "0 */1 * * * *") // Execute at 2 minutes
    @Transactional
    public void updateVehicleAvailableStatusInstantNow() {
        System.out.println("Running at: " +  Instant.now());
        Instant now = Instant.now();
        Instant plusOneMinute = now.plus((Duration.ofMinutes(1)));
        Instant minusTwoMinutes = now.minus(Duration.ofMinutes(2));

        // pegar todos os aluguel que tem o checkin para o instante atual ou que já passou e está o atributo running como falso.
        List<Rental> startingRentals = rentalRepository.findRentalsByCheckinDateTime(plusOneMinute, minusTwoMinutes);

        for(Rental rental : startingRentals){
            if(!rental.isRunning()) {
                Vehicle rentedVehicle = rental.getRentedVehicle();
                rentedVehicle.setAvailable(false);
                vehicleRepository.save(rentedVehicle);

                rental.setRunning(true);
                rentalRepository.save(rental);
            }
        }
    }

}
