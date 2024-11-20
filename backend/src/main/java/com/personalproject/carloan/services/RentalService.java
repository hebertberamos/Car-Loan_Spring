package com.personalproject.carloan.services;

import com.personalproject.carloan.calculators.RentalCalculator;
import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.mappers.RentalMapper;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.NotAvailableVehicleException;
import com.personalproject.carloan.services.exceptions.OutOfWorkingHoursException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import com.personalproject.carloan.services.exceptions.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RentalCalculator rentalCalculator;

    private final RentalMapper rentalMapper;

    public RentalService(RentalMapper rentalMapper) {
        this.rentalMapper = rentalMapper;
    }

    public Rental createRental(RentalDTO rentalDto, Vehicle vehicle, User user) throws OutOfWorkingHoursException {
        double rentalAmount = rentalCalculator.calculateRentalValue(rentalDto.getCheckin(), rentalDto.getCheckout(), vehicle.getPricePerHour(), vehicle.getPricePerDay());

        checkMoment(rentalDto.getCheckin());
        checkMoment(rentalDto.getCheckout());

        if(rentalDto.getCheckin().isBefore(rentalDto.getCheckout())){
            if(vehicle.isAvailable()) {
                Rental rental = new Rental();
                rental.setCheckin(rentalDto.getCheckin());
                rental.setCheckout(rentalDto.getCheckout());
                rental.setRefundMoment(null);
                rental.setRunning(false);
                rental.setUser(user);
                rental.setRentedVehicle(vehicle);
                rental.setRentalValue(rentalAmount);

                return repository.save(rental);
            }
            else {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm").withZone(ZoneId.systemDefault());
                String formatDate = dateTimeFormatter.format(vehicle.getRental().getCheckout());

                throw new NotAvailableVehicleException("The vehicle is not available... This vehicle is available just (" + formatDate + ") Fell free to choice another one available vehicle!");
            }
        }

        throw new OutOfWorkingHoursException("Checkin never can be after checkout");
    }


    @Transactional
    public RentalDTO createNewRentalToOtherUser(Long vehicleId, Long userId, RentalDTO rentalDto) {
        try{
            authenticationService.validateAdminRole();

            User user = userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User not found"));
            Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
            Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

            Rental rental = this.createRental(rentalDto, vehicle, user);

            return new RentalDTO(rental);
        }
        catch(NullPointerException e){
            throw new UnauthorizedException("Error!! You trying to do something you are note able!\nJust ADM can see this\n" + e.getMessage());
        }
        catch (OutOfWorkingHoursException e) {
            throw new OutOfWorkingHoursException("Erro!! \n" + e.getMessage());
        }
    }


    @Transactional(readOnly = true)
    public ShowRentalToUser findById(Long id){
        Optional<Rental> optional = repository.findById(id);
        Rental rental = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new ShowRentalToUser(rental);
    }

    @Transactional
    public RentalDTO update(Long id, RentalDTO dto){
        Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

            rental.setId(dto.getId());
            rental.setCheckin(dto.getCheckin());
            rental.setCheckout(dto.getCheckout());

        repository.save(rental);
        return rentalMapper.toRentalDto(rental);
    }

    @Transactional
    public RentalDTO delete(Long id){
        try {
            Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(rental);

            return rentalMapper.toRentalDto(rental);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    public Page<ShowRentalToUser> findAllFinishToday(Pageable pageable){
        Page<Rental> rentalEntities = repository.findAllFinishToday(pageable);
        return rentalEntities.map(x -> rentalMapper.toShowRentalToUser(x, new ShowUserToRental(x.getUser()), new ShowVehicleToRental(x.getRentedVehicle())));
    }

    public RentalDTO finishRental(Long id){
        try {
            authenticationService.validateAdminRole();

            Rental rentalEntity = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            Vehicle vehicleEntity = vehicleRepository.findById(rentalEntity.getRentedVehicle().getId()).orElseThrow(() -> new ResourcesNotFoundException("vehicle id " + id + " not found"));

            // update the vehicle available to true / update the rental running to false and the refund_moment adding the actual date.

            vehicleEntity.setAvailable(true);

            rentalEntity.setRunning(false);
            rentalEntity.setRefundMoment(Instant.now());

            vehicleRepository.save(vehicleEntity);
            repository.save(rentalEntity);

            return new RentalDTO(rentalEntity);
        }
        catch(UnauthorizedException e){
            throw new UnauthorizedException("Error!! You trying to do something you are note able!\nJust ADM can see this\n" + e.getMessage());
        }
    }


    private static void checkMoment(Instant moment) throws OutOfWorkingHoursException {
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(18, 0);

        LocalDateTime momentDateTime = moment.atZone(ZoneId.systemDefault()).toLocalDateTime();

//        LocalDate momentDate = moment.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate actualDate = LocalDate.now();

//        LocalDateTime time = moment.atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalTime timeNow = LocalTime.now();

        // Verify if the rental date is after now
        if(momentDateTime.toLocalDate().isAfter(actualDate)){
            if(momentDateTime.toLocalTime().isBefore(start) || momentDateTime.toLocalTime().isAfter(end)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }
//            return momentDateTime;
        // Verify if the rental is today, so need to verify if the hour is after the current hour.
        } else if(momentDateTime.toLocalDate().isEqual(actualDate)){
            if(momentDateTime.toLocalTime().isBefore(start) || momentDateTime.toLocalTime().isAfter(end) || momentDateTime.toLocalTime().isBefore(timeNow)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }
//            return momentDateTime;
        } else {
            throw new OutOfWorkingHoursException("This time can not be resolved");
        }
    }
}
