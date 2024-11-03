package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.*;
import com.personalproject.carloan.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDto(User user); // user to userDTO

    User toUser(UserDTO userDto); // userDTo to user

    UserInsertDTO toUserInsertDto(User user); // user to UserInsertDTO

    UserUpdateDTO toUserUpdateDto(User user); // User to UserUpdateDTO

    ShowUserToReview toShowUserToReviewDto(User user); // User to ShowUserToReview

    ShowUserToRental toShowUserToRentalDto(User user); // User to ShowUserToRental
}
