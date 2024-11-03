package com.personalproject.carloan.mappers;


import com.personalproject.carloan.dtos.CarDTO;
import com.personalproject.carloan.dtos.MotorcycleDTO;
import com.personalproject.carloan.dtos.ShowVehicleToRental;
import com.personalproject.carloan.dtos.VehicleDTO;
import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Motorcycle;
import com.personalproject.carloan.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO toVehicleDto(Vehicle vehicle);

    MotorcycleDTO toMotorcycleDto(Motorcycle motorcycle);

    Motorcycle toMotorcycle(MotorcycleDTO motorcycleDTO);

    Car toCar(CarDTO carDto);

    CarDTO toCarDto(Car car);
}
