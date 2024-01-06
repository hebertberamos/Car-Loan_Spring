package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.repositories.MotorcycleRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotorcycleService {

    @Autowired
    private MotorcycleRepository repository;

    @Transactional
    public List<MotorcycleDTO> findAll(){
        List<Motorcycle> motorcycles = repository.findAll();
        return motorcycles.stream().map(x -> new MotorcycleDTO(x)).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional
    public MotorcycleDTO findById(Long id){
        Optional<Motorcycle> optional = repository.findById(id);
        Motorcycle motorcycle = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new MotorcycleDTO(motorcycle);
    }

    // Atualizar
    @Transactional
    public MotorcycleDTO update(Long id, MotorcycleDTO dto){
        Motorcycle motorcycle = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        motorcycle.setId(dto.getId());
        motorcycle.setVehicleName(dto.getVehicleName());
        motorcycle.setBrand(dto.getBrand());
        motorcycle.setPlate(dto.getPlate());
        motorcycle.setYearManufacture(dto.getYearManufacture());
        motorcycle.setStatusVehicle(dto.getStatusVehicle());
        motorcycle.setDescription(dto.getDescription());
        motorcycle.setPricePerHour(dto.getPricePerHour());
        motorcycle.setPricePerDay(dto.getPricePerDay());
        motorcycle.setHasFairing(dto.getHasFairing());

        repository.save(motorcycle);
        return new MotorcycleDTO(motorcycle);
    }

    // Deletar
    @Transactional
    public MotorcycleDTO delete(Long id){
        try {
            Motorcycle motorcycle = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(motorcycle);

            return new MotorcycleDTO(motorcycle);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
    public MotorcycleDTO insert(MotorcycleDTO dto){
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(dto.getId());
        motorcycle.setVehicleName(dto.getVehicleName());
        motorcycle.setBrand(dto.getBrand());
        motorcycle.setPlate(dto.getPlate());
        motorcycle.setYearManufacture(dto.getYearManufacture());
        motorcycle.setStatusVehicle(dto.getStatusVehicle());
        motorcycle.setDescription(dto.getDescription());
        motorcycle.setPricePerHour(dto.getPricePerHour());
        motorcycle.setPricePerDay(dto.getPricePerDay());
        motorcycle.setHasFairing(dto.getHasFairing());

        repository.save(motorcycle);
        dto = new MotorcycleDTO(motorcycle);
        return dto;
    }

}
