package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.CreateRentalDTO;
import com.personalproject.carloan.dtos.RentalDTO;
import com.personalproject.carloan.entities.Rental;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.entities.Vehicle;
import com.personalproject.carloan.repositories.DeliverRepository;
import com.personalproject.carloan.repositories.RentalRepository;
import com.personalproject.carloan.repositories.UserRepository;
import com.personalproject.carloan.repositories.VehicleRepository;
import com.personalproject.carloan.services.exceptions.ResourcesNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DeliverRepository deliverRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional(readOnly = true)
    public List<RentalDTO> findAll(){
        List<Rental> rentals = repository.findAll();
        return rentals.stream().map(x -> new RentalDTO(x.getCheckin(), x.getCheckout(), x.getRefundMoment(), x.isRunning(), x.getDeliver(), x.getPayment(), x.getUser(), x.getRentedVehicle())).collect(Collectors.toList());
    }

    // Buscando pelo id
    @Transactional(readOnly = true)
    public RentalDTO findById(Long id){
        Optional<Rental> optional = repository.findById(id);
        Rental rental = optional.orElseThrow(() -> new ResourcesNotFoundException("Id not found"));
        return new RentalDTO(rental);
    }

    // Atualizar
    @Transactional
    public RentalDTO update(Long id, RentalDTO dto){
        Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Id not found"));

        rental.setId(dto.getId());
        rental.setCheckin(dto.getCheckin());
        rental.setCheckout(dto.getCheckout());

        repository.save(rental);
        return dto = new RentalDTO(rental);
    }

    // Deletar
    @Transactional
    public RentalDTO delete(Long id){
        try {
            Rental rental = repository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("id " + id + " not found"));
            repository.delete(rental);

            return new RentalDTO(rental);
        }
        catch (EmptyResultDataAccessException e){
            System.out.println("Element not found");
        }

        return null;
    }

    //Inserir
   /* public String createNewByVehicleId(Long vehicleId){

        // =>  Identify the user making the request
        User user = authenticationService.authenticated();

        // =>  Identify the vehicle the user are renting
        Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = optional.orElseThrow(() -> new ResourcesNotFoundException("Vehicle not found"));

        // =>  Create a new Rental by a dto and save at database
        Rental entity = repository.save(new Rental());


        return "User = " + user + "\nVehicle = " + vehicle;

    }*/

}
