package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.ClientDTO;
import com.personalproject.carloan.entities.Client;
import com.personalproject.carloan.repositories.ClientRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public List<ClientDTO> findAll(){
        List<Client> clients = repository.findAll();
        return clients.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional
    public ClientDTO findById(Long id){
        Optional<Client> optional = repository.findById(id);
        Client client = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new ClientDTO(client);
    }

    // Atualizar


    // Deletar


    //Inserir



}
