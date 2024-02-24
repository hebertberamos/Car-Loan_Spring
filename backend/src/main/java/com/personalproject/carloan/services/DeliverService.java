package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.DeliverDTO;
import com.personalproject.carloan.entities.Deliver;
import com.personalproject.carloan.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository repository;

    @Transactional(readOnly = true)
    public List<DeliverDTO> findAll(){
        List<Deliver> deliveries = repository.findAll();
        return deliveries.stream().map(x -> new DeliverDTO(x)).collect(Collectors.toList());
    }

}
