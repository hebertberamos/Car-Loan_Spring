package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Notification;
import com.personalproject.carloan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE " +
            "(n.user = :user) AND " +
            "(:unreadOnly = false OR n.read = false) " +
            "ORDER BY n.moment DESC")
    List<Notification> findNotifications(User user, boolean unreadOnly);

}
