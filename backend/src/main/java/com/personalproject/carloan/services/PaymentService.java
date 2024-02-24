package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.PaymentDTO;
import com.personalproject.carloan.dtos.ReviewDTO;
import com.personalproject.carloan.entities.Payment;
import com.personalproject.carloan.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Transactional(readOnly = true)
    public List<PaymentDTO> findAll(){
        List<Payment> payments = repository.findAll();
        return payments.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
    }

}
