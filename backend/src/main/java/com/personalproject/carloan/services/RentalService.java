package com.personalproject.carloan.services;

import com.personalproject.carloan.calculators.RentalCalculator;
import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.RentNote;
import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.mappers.RentalMapper;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.senders.RentalDocumentMessageSender;
import com.personalproject.carloan.services.exceptions.NotAvailableVehicleException;
import com.personalproject.carloan.services.exceptions.OutOfWorkingHoursException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import com.personalproject.carloan.services.exceptions.UnauthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private RentalDocumentMessageSender documentMessageSender;

    private final RentalMapper rentalMapper;

    public RentalService(RentalMapper rentalMapper) {
        this.rentalMapper = rentalMapper;
    }

    public Rental createRental(RentalDTO rentalDto, Vehicle vehicle, User user){
        double rentalAmount = rentalCalculator.calculateRentalValue(rentalDto.getCheckin(), rentalDto.getCheckout(), vehicle.getPricePerHour(), vehicle.getPricePerDay());

        List<Rental> rentalsInProgress = repository.findAllRentalsRunning();

        try {
            checkRequestedRentalDates(rentalDto.getCheckin(), rentalDto.getCheckout(), vehicle);

            if (rentalDto.getCheckin().isBefore(rentalDto.getCheckout())) {
                if (vehicle.isAvailable()) {

                    // Check if the user already is in a rental.
                    for (Rental r : rentalsInProgress) {
                        if (r.getUser().getId() == user.getId()) {
                            throw new Exception("TEST | User already in a Rental");
                        }
                    }

                    Rental rental = new Rental();
                    rental.setCheckin(rentalDto.getCheckin());
                    rental.setCheckout(rentalDto.getCheckout());
                    rental.setRefundMoment(null);
                    rental.setRunning(false);
                    rental.setUser(user);
                    rental.setRentedVehicle(vehicle);
                    rental.setRentalValue(rentalAmount);

                    //Send the message (rent note) to the queue to process and generate the contract pdf file
                    ZoneId zoneId = ZoneId.systemDefault();

                    LocalDateTime checkinDateTime = LocalDateTime.ofInstant(rental.getCheckin(), zoneId);
                    LocalDateTime checkoutDateTime = LocalDateTime.ofInstant(rental.getCheckout(), zoneId);

                    RentNote rentNote = new RentNote(/*rental.getUser().getName(), rental.getUser().getEmail(), rental.getUser().getCpf(), rental.getRentedVehicle().getName(), rental.getRentedVehicle().getBrand(), rental.getRentedVehicle().getPlate(), checkinDateTime, checkoutDateTime, null, rental.getRentalValue(), null*/);
                    rentNote.setUserName(rental.getUser().getName());
                    rentNote.setUserEmail(rental.getUser().getEmail());
                    rentNote.setUserCpf(rental.getUser().getCpf());
                    rentNote.setVehicleName(rental.getRentedVehicle().getName());
                    rentNote.setVehicleBrand(rental.getRentedVehicle().getBrand());
                    rentNote.setVehiclePlate(rental.getRentedVehicle().getPlate());
                    rentNote.setRentalCheckin(checkinDateTime);
                    rentNote.setRentalCheckout(checkoutDateTime);
                    rentNote.setRefundMoment(null);
                    rentNote.setFirstRentalValue(rental.getRentalValue());
                    rentNote.setLastRentalValue(null);


                    documentMessageSender.feedDocumentGeneratorQueue(rentNote);
                    System.out.printf("INFO |-| Document %s - %s - %s created!", rental.getUser().getName(), rental.getUser().getCpf(), rental.getRentedVehicle().getName());

                    return repository.save(rental);
                } else {
                    Rental currentRental = repository.findCurrentByVehicleId(vehicle.getId());

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm").withZone(ZoneId.systemDefault());
                    String formatDate = dateTimeFormatter.format(currentRental.getCheckout());

                    throw new NotAvailableVehicleException("The vehicle is not available... This vehicle is available just (" + formatDate + ") Fell free to choice another one available vehicle!");
                }


            }
        } catch (Exception e){
            System.out.println("ERROR | " + e.getMessage());
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

    public Page<ShowRentalToUser> findRentals(boolean finishTodayOnly, boolean alreadyFinishedOnly, boolean runningOnly, LocalDate checkinDate, LocalDate endDate, Pageable pageable){

        Page<Rental> rentalEntities = repository.findRentals(finishTodayOnly, alreadyFinishedOnly, runningOnly, checkinDate, endDate, pageable);
        return rentalEntities.map(x -> rentalMapper.toShowRentalToUser(x, new ShowUserToRental(x.getUser()), new ShowVehicleToRental(x.getRentedVehicle())));
    }

    public RentalDTO finishRental(Long id){
        try {
            authenticationService.validateAdminRole();

            Rental rentalEntity = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Rental id " + id + " not found"));
            Vehicle vehicleEntity = vehicleRepository.findById(rentalEntity.getRentedVehicle().getId()).orElseThrow(() -> new ResourcesNotFoundException("vehicle id " + id + " not found"));

            vehicleEntity.setAvailable(true);

            rentalEntity.setRunning(false);
            rentalEntity.setRefundMoment(Instant.now());

            double refundTax = rentalCalculator.calculateRefoundTax(rentalEntity.getCheckout(), rentalEntity.getRefundMoment(), rentalEntity.getRentalValue());
            double totalRentalValue = rentalEntity.getRentalValue() + refundTax;

            rentalEntity.setRentalValue(totalRentalValue);

            vehicleRepository.save(vehicleEntity);
            repository.save(rentalEntity);


            return new RentalDTO(rentalEntity);
        }
        catch(UnauthorizedException e){
            throw new UnauthorizedException("Error!! You trying to do something you are note able!\nJust ADM can see this\n" + e.getMessage());
        }
    }


    private void checkRequestedRentalDates(Instant checkin, Instant checkout,  Vehicle vehicle) throws OutOfWorkingHoursException {

        LocalDateTime checkinDateTime = checkin.atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime checkoutDateTime = checkout.atZone(ZoneId.systemDefault()).toLocalDateTime();

        checkMoment(checkinDateTime);
        checkMoment(checkoutDateTime);

        // Get all vehicle's rentals
        List<Rental> allVehiclesRental = repository.findAllByVehicleId(vehicle.getId()); // Get all scheduled or running vehicle's rental
        List<LocalDate> requestedDates = getDateBetween(checkin, checkout); // Dates between checkin and checkout rental.

        List<LocalDate> rentalDates = new ArrayList<>(); // To save all the rental dates
        List<LocalDate> unavailableDates = new ArrayList<>(); // To save all the unavailable vehicle dates.

        //Catch all Rental dates.
        for(Rental r : allVehiclesRental){
            rentalDates = getDateBetween(r.getCheckin(), r.getCheckout());
            // Add the captured dates to the list with all dates.
            for(LocalDate date : rentalDates){
                unavailableDates.add(date);
            }
        }

        // Verify if the request dates are the same or if it's between some scheduled or running rental.
       verifyIfRequestedDatesIsAvailable(checkin, checkout, requestedDates, unavailableDates, allVehiclesRental);
    }

    private void checkMoment(LocalDateTime moment){
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(18, 0);

        LocalDate actualDate = LocalDate.now();

        LocalTime timeNow = LocalTime.now();

        // Verify if the rental date is after now
        if(moment.toLocalDate().isAfter(actualDate)){
            if(moment.toLocalTime().isBefore(start) || moment.toLocalTime().isAfter(end)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }
            // Verify if the rental is today, so need to verify if the hour is after the current hour.
        } else if(moment.toLocalDate().isEqual(actualDate)){
            if(moment.toLocalTime().isBefore(start) || moment.toLocalTime().isAfter(end) || moment.toLocalTime().isBefore(timeNow)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }
        } else {
            throw new OutOfWorkingHoursException("This time can not be resolved");
        }
    }

    private List<LocalDate> getDateBetween(Instant checkin, Instant checkout) {
        List<LocalDate> dates = new ArrayList<>();


        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime checkinDateTime = LocalDateTime.ofInstant(checkin, zoneId);
        LocalDateTime checkoutDateTime = LocalDateTime.ofInstant(checkout, zoneId);

        LocalDate checkinDate = checkinDateTime.toLocalDate().plusDays(1);
        LocalDate checkoutDate = checkoutDateTime.toLocalDate();

        while (checkinDate.isBefore(checkoutDate)) {
            dates.add(checkinDate);
            checkinDate = checkinDate.plusDays(1);
        }

        return dates;
    }

    private void verifyIfRequestedDatesIsAvailable(Instant checkin, Instant checkout, List<LocalDate> datesBetweenCheckinAndCheckout, List<LocalDate> unavailableDates, List<Rental> allVehiclesRental){
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDate checkinDate = checkin.atZone(zoneId).toLocalDate();
        LocalDate checkoutDate = checkout.atZone(zoneId).toLocalDate();

        for(LocalDate unavailableDate : unavailableDates){
            if(unavailableDate.isEqual(checkinDate) || unavailableDate.isEqual(checkoutDate)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }
            for(LocalDate requestedDates : datesBetweenCheckinAndCheckout){
                if(unavailableDate.isEqual(requestedDates)){
                    throw new OutOfWorkingHoursException("This time can not be resolved");
                }
            }
        }

        LocalDateTime checkinDateTime = LocalDateTime.ofInstant(checkin, zoneId);
        LocalDateTime checkoutDateTime = LocalDateTime.ofInstant(checkout, zoneId);

        for(Rental scheduledRental : allVehiclesRental){
            LocalDateTime scheduledRentalCheckinDateTime = LocalDateTime.ofInstant(scheduledRental.getCheckin(), zoneId);
            LocalDateTime scheduledRentalCheckoutDateTime = LocalDateTime.ofInstant(scheduledRental.getCheckout(), zoneId);

            if(scheduledRentalCheckinDateTime.isEqual(checkinDateTime) || scheduledRentalCheckinDateTime.isEqual(checkoutDateTime) || scheduledRentalCheckoutDateTime.isEqual(checkinDateTime) || scheduledRentalCheckoutDateTime.isEqual(checkoutDateTime)){
                throw new OutOfWorkingHoursException("This time can not be resolved");
            }

        }

    }

}
