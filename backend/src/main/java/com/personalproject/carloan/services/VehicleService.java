package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.*;
import com.personalproject.carloan.repositories.CarRepository;
import com.personalproject.carloan.repositories.MotorcycleRepository;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.DatabaseException;
import com.personalproject.carloan.services.exceptions.ForbiddenException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ReviewService reviewService;

    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAll(boolean availableOnly, String brand, String vehicleName, Integer vehicleType, Pageable pageable){

        Page<Vehicle> page = repository.findVehicles(availableOnly, brand, vehicleName, vehicleType, pageable);
        return page.map(x -> new VehicleDTO(x.getId(), x.getImg(), x.getName(), x.getBrand(), x.getStatus(), x.isAvailable(), x.getRating(), x.getPricePerHour(), x.getPricePerDay()));
    }

    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id){
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        if(entity instanceof Car carEntity) {
            return new CarDTO(carEntity);
        }
        else if(entity instanceof Motorcycle motorcycleEntity){
            return new MotorcycleDTO(motorcycleEntity);
        }

        return new VehicleDTO(entity);
    }


    // =>  Methos to create a new Motorcycle
    @Transactional
    public MotorcycleDTO createMotorcycle(MotorcycleDTO dto){
        Motorcycle entity = repository.save(new Motorcycle(dto.getImg(), dto.getVehicleName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getVehicleStatus(), dto.getVehicleDescription(), dto.isAvailable(), dto.getRating(), /*dto.getVehicleType(),*/ true, null));
        repository. save(entity);

        return new MotorcycleDTO(entity);
    }

    // =>  Method to create a new Car
    @Transactional
    public CarDTO createCar(CarDTO dto) {
        Car carEntity = new Car(dto.getImg(), dto.getVehicleName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getVehicleStatus(), dto.getVehicleDescription(), dto.isAvailable(), dto.getRating(), dto.getNumberOfDoors(), dto.getTrunkSpace(), dto.isHasStep(), null);
        repository.save(carEntity);

        CarDTO carDto = new CarDTO(carEntity);
        return carDto;
    }

    @Transactional
    public void deleteById(Long id){
        try{
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e){
            throw new ResourcesNotFoundException("Id not found (" + id + ")");
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("This vehicle can't be deleted. LINKD VEHICLE");
        }
    }

    @Transactional
    public CarDTO updateCar(Long id, CarDTO dto){
        Optional<Car> optional = carRepository.findById(id);
        Car entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        //fazer as alterações na entidade a partir das informações que vieram do DTO
        entity.setPricePerDay(dto.getPricePerDay());
        entity.setPricePerHour(dto.getPricePerHour());
        entity.setImg(dto.getImg());
        entity.setStatus(dto.getVehicleStatus());
        entity.setDescription(dto.getVehicleDescription());
        entity.setHasStep(dto.isHasStep());

        carRepository.save(entity);

        dto = new CarDTO(entity);
        return dto;
    }

    public MotorcycleDTO updateMotorcycle(Long id, MotorcycleDTO dto){
        Optional<Motorcycle> optional = motorcycleRepository.findById(id);
        Motorcycle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        //fazer as alterações na entidade a partir das informações que vieram do DTO
        entity.setPricePerDay(dto.getPricePerDay());
        entity.setPricePerHour(dto.getPricePerHour());
        entity.setImg(dto.getImg());
        entity.setStatus(dto.getVehicleStatus());
        entity.setDescription(dto.getVehicleDescription());
        entity.setHasFairing(dto.isHasFairing());

        motorcycleRepository.save(entity);

        dto = new MotorcycleDTO(entity);
        return dto;
    }

    @Transactional
    public RentalDTO createRentalByVehicle(Long id, RentalDTO rentalDto){

        // =>  Identify the user making the request
        User user = authenticationService.authenticated();

        // =>  Identify the vehicle the user are renting
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

        // =>  Create a new Rental by a dto and save at database
        Rental rental = new Rental();
        rental.setCheckin(rentalDto.getCheckin());
        rental.setCheckout(rentalDto.getCheckout());
        rental.setRefundMoment(null);
        rental.setRunning(true);
        rental.setUser(user);
        rental.setRentedVehicle(vehicle);

        rental = rentalRepository.save(rental);
        return new RentalDTO(rental);
    }

    @Transactional
    public ReviewDTO newReviewToVehicle(Long id, ReviewDTO reviewDto){
        User user = authenticationService.authenticated();

        Optional<Vehicle> optional = repository.findById(id);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

        List<Rental> allRentals = rentalRepository.findAll();
        List<Rental> vehicleRentals = new ArrayList<>();

        // =>  Get all rentals with the vehicle
        for(Rental rental : allRentals){
            if(rental.getRentedVehicle().getId() == id){
                vehicleRentals.add(rental);
            }
        }

        // =>  Verify if user has rented this vehicle
        for(Rental rental : vehicleRentals){
            if(rental.getUser().getId() == user.getId()){
                return reviewService.insert(reviewDto, user, vehicle);
            }
        }
        throw new ForbiddenException("Action blocked, you cannot leave a comment as you have never rented this vehicle");
    }
}