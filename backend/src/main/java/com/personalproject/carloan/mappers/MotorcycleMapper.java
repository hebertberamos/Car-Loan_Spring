package com.personalproject.carloan.mappers;

import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.entities.Motorcycle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MotorcycleMapper {

    MotorcycleMapper INSTANCE = Mappers.getMapper(MotorcycleMapper.class);



}
