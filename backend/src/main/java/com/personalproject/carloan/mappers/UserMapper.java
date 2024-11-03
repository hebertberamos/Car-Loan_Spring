package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.ShowUserToReview;
import com.personalproject.carloan.dtos.UserDTO;
import com.personalproject.carloan.dtos.UserInsertDTO;
import com.personalproject.carloan.dtos.UserUpdateDTO;
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
}
