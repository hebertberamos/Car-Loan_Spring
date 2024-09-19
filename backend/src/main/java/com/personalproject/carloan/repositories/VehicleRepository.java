package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:availableOnly = false OR v.available = true) AND " +
            "(LOWER(v.brand) LIKE LOWER(CONCAT ('%',:brand,'%')) OR v.brand = '') AND " +
            "(LOWER(v.vehicleName) LIKE LOWER(CONCAT ('%',:vehicleName,'%')) OR v.vehicleName = '') " +
            "ORDER BY v.vehicleId")
    Page<Vehicle> findVehicles(boolean availableOnly, String brand, String vehicleName, Pageable pageable);
}
