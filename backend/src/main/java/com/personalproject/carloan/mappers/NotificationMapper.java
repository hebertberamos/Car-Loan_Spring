package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.NotificationDTO;
import com.personalproject.carloan.dtos.ShowNotificationToUserDTO;
import com.personalproject.carloan.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationDTO toNotificationDto(Notification notification);

    Notification toNotification(NotificationDTO notificationDTO);

    ShowNotificationToUserDTO toShowNotificationToUserDTO(Notification notification);
}
