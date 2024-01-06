package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.CarDTO;
import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.repositories.CarRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    @Transactional
    public List<CarDTO> findAll(){
        List<Car> cars = repository.findAll();
        return cars.stream().map(x -> new CarDTO(x)).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional
    public CarDTO findById(Long id){
        Optional<Car> optional = repository.findById(id);
        Car car = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new CarDTO(car);
    }

    // Atualizar
    @Transactional
    public CarDTO update(Long id, CarDTO dto){
        Car car = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        car.setId(dto.getId());
        car.setVehicleName(dto.getVehicleName());
        car.setBrand(dto.getBrand());
        car.setPlate(dto.getPlate());
        car.setYearManufacture(dto.getYearManufacture());
        car.setStatusVehicle(dto.getStatusVehicle());
        car.setDescription(dto.getDescription());
        car.setPricePerHour(dto.getPricePerHour());
        car.setPricePerDay(dto.getPricePerDay());
        car.setNumberOfDoors(dto.getNumberOfDoors());

        repository.save(car);
        return dto = new CarDTO(car);
    }

    // Deletar
    @Transactional
    public CarDTO delete(Long id){
        try {
            Car car = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(car);

            return new CarDTO(car);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
    public CarDTO insert(CarDTO dto){
        Car car = new Car();

        car.setId(dto.getId());
        car.setVehicleName(dto.getVehicleName());
        car.setBrand(dto.getBrand());
        car.setPlate(dto.getPlate());
        car.setYearManufacture(dto.getYearManufacture());
        car.setStatusVehicle(dto.getStatusVehicle());
        car.setDescription(dto.getDescription());
        car.setPricePerHour(dto.getPricePerHour());
        car.setPricePerDay(dto.getPricePerDay());
        car.setNumberOfDoors(dto.getNumberOfDoors());

        repository.save(car);
        dto = new CarDTO(car);
        return dto;
    }

}
