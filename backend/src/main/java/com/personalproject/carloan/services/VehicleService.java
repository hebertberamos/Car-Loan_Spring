package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public Page<VehicleDTO> findAll(Pageable pageable){
        Page<Vehicle> page = repository.findAll(pageable);
        return page.map(x -> new VehicleDTO(x));
    }
}
