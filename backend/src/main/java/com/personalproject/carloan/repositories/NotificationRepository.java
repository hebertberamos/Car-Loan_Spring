package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Notification;
import com.personalproject.carloan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);

}
