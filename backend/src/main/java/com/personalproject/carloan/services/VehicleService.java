package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.*;
import com.personalproject.carloan.mappers.RentalMapper;
import com.personalproject.carloan.mappers.VehicleMapper;
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

    @Autowired
    private RentalService rentalService;

    private final VehicleMapper vehicleMapper;
    private final RentalMapper rentalMapper;

    public VehicleService(VehicleMapper vehicleMapper, RentalMapper rentalMapper) {
        this.vehicleMapper = vehicleMapper;
        this.rentalMapper = rentalMapper;
    }
    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAll(boolean availableOnly, String brand, String vehicleName, Integer vehicleType, Pageable pageable){

        Page<Vehicle> page = repository.findVehicles(availableOnly, brand, vehicleName, vehicleType, pageable);
        return page.map(x -> vehicleMapper.toVehicleDto(x));
    }

    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id){
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        if(entity instanceof Car carEntity) {
            return vehicleMapper.toCarDto(carEntity);
        }
        else if(entity instanceof Motorcycle motorcycleEntity){
            return vehicleMapper.toMotorcycleDto(motorcycleEntity);
        }

        return vehicleMapper.toVehicleDto(entity);
    }


    @Transactional
    public MotorcycleDTO createMotorcycle(MotorcycleDTO dto){
        Motorcycle entity = repository.save(new Motorcycle(dto.getImg(), dto.getVehicleName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getVehicleStatus(), dto.getVehicleDescription(), dto.isAvailable(), dto.getRating(), /*dto.getVehicleType(),*/ true, null));
        repository. save(entity);

        return vehicleMapper.toMotorcycleDto(entity);
    }

    @Transactional
    public CarDTO createCar(CarDTO dto) {
        Car carEntity = new Car(dto.getImg(), dto.getVehicleName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getVehicleStatus(), dto.getVehicleDescription(), dto.isAvailable(), dto.getRating(), dto.getNumberOfDoors(), dto.getTrunkSpace(), dto.isHasStep(), null);
        repository.save(carEntity);
        return vehicleMapper.toCarDto(carEntity);
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

        if(dto.getPricePerDay() != null){
            entity.setPricePerDay(dto.getPricePerDay());
        }

        if(dto.getPricePerHour() != null || dto.getPricePerHour() != 0.0){
            entity.setPricePerHour(dto.getPricePerHour());
        }

        entity.setImg(dto.getImg());
        entity.setStatus(dto.getVehicleStatus());
        entity.setDescription(dto.getVehicleDescription());
        entity.setHasStep(dto.isHasStep());

        carRepository.save(entity);

        return vehicleMapper.toCarDto(entity);
    }

    public MotorcycleDTO updateMotorcycle(Long id, MotorcycleDTO dto){
        Optional<Motorcycle> optional = motorcycleRepository.findById(id);
        Motorcycle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        if(dto.getPricePerDay() != null){
            entity.setPricePerDay(dto.getPricePerDay());
        }

        if(dto.getPricePerHour() != null || dto.getPricePerHour() != 0.0){
            entity.setPricePerHour(dto.getPricePerHour());
        }

        entity.setImg(dto.getImg());
        entity.setStatus(dto.getVehicleStatus());
        entity.setDescription(dto.getVehicleDescription());
        entity.setHasFairing(dto.isHasFairing());

        motorcycleRepository.save(entity);

        return vehicleMapper.toMotorcycleDto(entity);
    }

    @Transactional
    public ShowRentalToUser createRentalByVehicle(Long id, RentalDTO rentalDto) throws Exception {

        // =>  Identify the user making the request
        User user = authenticationService.authenticated();

        Optional<Vehicle> optional = repository.findById(id);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

        Rental rental = rentalService.createRental(rentalDto, vehicle, user);

        vehicle.setAvailable(false);
        repository.save(vehicle);

        rental = rentalRepository.save(rental);

        ShowRentalToUser rentalResponse = new ShowRentalToUser(rental);
        return rentalResponse;
    }

    @Transactional
    public ReviewDTO newReviewToVehicle(Long id, ReviewDTO reviewDto){
        User user = authenticationService.authenticated();

        Optional<Vehicle> optional = repository.findById(id);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

        List<Rental> allRentals = rentalRepository.findAll();
        List<Rental> vehicleRentals = new ArrayList<>();

        // =>  Get all vehicle's rentals
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