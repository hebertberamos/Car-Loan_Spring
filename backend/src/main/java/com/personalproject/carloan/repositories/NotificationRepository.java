package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Notification;
import com.personalproject.carloan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT tb_notification.id AS id, " +
            "tb_notification.notification_text, " +
            "tb_notification.moment, " +
            "tb_notification.readed, " +
            "tb_user.id AS user_id, " +
            "tb_user.user_name " +
            "FROM tb_notification " +
            "INNER JOIN tb_user " +
            "ON tb_notification.user_id = tb_user.id " +
            "WHERE tb_user.email = :userEmail " +
            "AND (:unreadOnly = false OR tb_notification.readed = false)", nativeQuery = true)
    List<Notification> findNotifications(@Param("userEmail") String userEmail, boolean unreadOnly);

}
