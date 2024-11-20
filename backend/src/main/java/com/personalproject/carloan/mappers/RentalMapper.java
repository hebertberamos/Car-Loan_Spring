package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.RentalDTO;
import com.personalproject.carloan.dtos.ShowRentalToUser;
import com.personalproject.carloan.dtos.ShowUserToRental;
import com.personalproject.carloan.dtos.ShowVehicleToRental;
import com.personalproject.carloan.entities.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);

    RentalDTO toRentalDto(Rental rental);

    Rental toRental(RentalDTO rentalDTO);

    ShowRentalToUser toShowRentalToUser(Rental rental, ShowUserToRental user, ShowVehicleToRental vehicle);

}
