package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM TB_VEHICLE WHERE TYPE_VEHICLE = :typeVehicle", nativeQuery = true)
    List<Vehicle> getAllVehicleTypeCar(String typeVehicle);
}
