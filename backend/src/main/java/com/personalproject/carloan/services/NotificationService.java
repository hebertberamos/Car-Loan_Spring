package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.NotificationDTO;
import com.personalproject.carloan.entities.Notification;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional(readOnly = true)
    public List<NotificationDTO> findByUser(){
        User user = authenticationService.authenticated();

        List<Notification> notifications = repository.findByUser(user);
        return notifications.stream().map(x -> new NotificationDTO(x)).collect(Collectors.toList());
    }

}
