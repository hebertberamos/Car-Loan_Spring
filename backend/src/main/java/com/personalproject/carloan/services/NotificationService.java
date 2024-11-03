package com.personalproject.carloan.services;

import com.personalproject.carloan.dtos.NotificationDTO;
import com.personalproject.carloan.entities.Notification;
import com.personalproject.carloan.entities.User;
import com.personalproject.carloan.mappers.NotificationMapper;
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

    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationMapper notificationMapper){
        this.notificationMapper = notificationMapper;
    }

    @Transactional(readOnly = true)
    public List<NotificationDTO> findAll(boolean unreadOnly){
        User user = authenticationService.authenticated();

        String userEmail = user.getEmail();
        List<Notification> notifications = repository.findNotifications(userEmail, unreadOnly);
        return notifications.stream().map(notificationMapper::toNotificationDto).collect(Collectors.toList());
    }

}
