package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.ClientDTO;
import com.personalproject.carloan.entities.Client;
import com.personalproject.carloan.repositories.ClientRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        Client client = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setCpf(dto.getCpf());
        client.setAge(dto.getAge());

        repository.save(client);
        dto = new ClientDTO(client);
        return dto;
    }

    // Deletar
    @Transactional
    public ClientDTO delete(Long id){
        try {
            Client client = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(client);

            return new ClientDTO(client);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setCpf(dto.getCpf());
        client.setAge(dto.getAge());

        repository.save(client);
        dto = new ClientDTO(client);
        return dto;
    }


}
