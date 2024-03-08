package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.CarDTO;
import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.DatabaseException;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAll(Pageable pageable){
        Page<Vehicle> page = repository.findAll(pageable);
        return page.map(x -> new VehicleDTO(x.getName(), x.getBrand(), x.getStatus(), x.isAvailable(), x.getRating()));
    }

    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id){
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new VehicleDTO(entity);
    }

    @Transactional
    public VehicleDTO update(Long id, VehicleDTO dto){
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle entity = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setPlate(dto.getPlate());
        entity.setManufactureYear(dto.getManufactureYear());
        entity.setDescription(dto.getDescription());
        entity.setPricePerHour(dto.getPricePerHour());
        entity.setPricePerDay(dto.getPricePerDay());
        entity.setAvailable(dto.isAvailable());

        return new VehicleDTO(entity);
    }


    // =>  Methos to create a new Motorcycle
    @Transactional
    public MotorcycleDTO createMotorcycle(MotorcycleDTO dto){

        Motorcycle entity = repository.save(new Motorcycle(dto.getName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getStatus(), dto.getDescription(), dto.getPricePerHour(), dto.getPricePerDay(), dto.isAvailable(), dto.getRating(), true, null));
        return new MotorcycleDTO(entity);
    }

    // =>  Method to create a new Car
    @Transactional
    public CarDTO createCar(CarDTO dto) {
        Car entity = repository.save(new Car(dto.getName(), dto.getBrand(), dto.getPlate(), dto.getManufactureYear(), dto.getStatus(), dto.getDescription(), dto.getPricePerHour(), dto.getPricePerDay(), dto.isAvailable(), dto.getRating(), dto.getNumberOfDoors(), dto.getTrunkSpace(), dto.isHasStep(), null));
        return new CarDTO(entity);
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
}