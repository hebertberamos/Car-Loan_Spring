package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Transactional
    public List<VehicleDTO> findAll(){
        List<Vehicle> vehicles = repository.findAll();
        return vehicles.stream().map(x -> new VehicleDTO(x)).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional
    public VehicleDTO findById(Long id){
        Optional<Vehicle> optional = repository.findById(id);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new VehicleDTO(vehicle);
    }

    //Buscando todos o veículos que são do tipo carro
    @Transactional
    public List<VehicleDTO> findAllVehicleTypeCar(String typeVehicle){
        List<Vehicle> cars = repository.getAllVehicleTypeCar(typeVehicle);
        return cars.stream().map(x -> new VehicleDTO(x)).collect(Collectors.toList());
    }

    // Atualizar
    @Transactional
    public VehicleDTO update(Long id, VehicleDTO dto){
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        vehicle.setId(dto.getId());
        vehicle.setTypeVehicle(dto.getTypeVehicle());
        vehicle.setVehicleName(dto.getVehicleName());
        vehicle.setBrand(dto.getBrand());
        vehicle.setPlate(dto.getPlate());
        vehicle.setYearManufacture(dto.getYearManufacture());
        vehicle.setStatusVehicle(dto.getStatusVehicle());
        vehicle.setDescription(dto.getDescription());
        vehicle.setPricePerHour(dto.getPricePerHour());
        vehicle.setPricePerDay(dto.getPricePerDay());

        repository.save(vehicle);
        return dto = new VehicleDTO(vehicle);
    }

    // Deletar
    @Transactional
    public VehicleDTO delete(Long id){
        try {
            Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(vehicle);

            return new VehicleDTO(vehicle);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
    public VehicleDTO insert(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();

        vehicle.setId(dto.getId());
        vehicle.setTypeVehicle(dto.getTypeVehicle());
        vehicle.setVehicleName(dto.getVehicleName());
        vehicle.setBrand(dto.getBrand());
        vehicle.setPlate(dto.getPlate());
        vehicle.setYearManufacture(dto.getYearManufacture());
        vehicle.setStatusVehicle(dto.getStatusVehicle());
        vehicle.setDescription(dto.getDescription());
        if (Objects.equals(dto.getTypeVehicle(), "MOTORCYCLE")) {
            switch (dto.getStatusVehicle()){
                case "VIP" : {
                    vehicle.setPricePerHour(100.0);
                    vehicle.setPricePerDay(300.0);
                    break;
                }
                case "POPULAR" : {
                    vehicle.setPricePerHour(50.0);
                    vehicle.setPricePerDay(200.0);
                    break;
                }
                case "ANTIQUITY" : {
                    vehicle.setPricePerHour(250.0);
                    vehicle.setPricePerDay(600.0);
                    break;
                }
                default :
                    throw new IllegalArgumentException("Vehicle status not identify");
            }
        }

        if(Objects.equals(dto.getTypeVehicle(), "CAR")){
            switch (dto.getStatusVehicle()){
                case "VIP" : {
                    vehicle.setPricePerHour(120.0);
                    vehicle.setPricePerDay(500.0);
                    break;
                }
                case "POPULAR" : {
                    vehicle.setPricePerHour(70.0);
                    vehicle.setPricePerDay(300.0);
                    break;
                }
                case "ANTIQUITY" : {
                    vehicle.setPricePerHour(350.0);
                    vehicle.setPricePerDay(1000.0);
                    break;
                }
                default :
                    throw new IllegalArgumentException("Vehicle status not identify");
            }
        }


        repository.save(vehicle);
        dto = new VehicleDTO(vehicle);
        return dto;
    }
}
