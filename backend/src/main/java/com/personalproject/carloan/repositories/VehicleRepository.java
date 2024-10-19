package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:availableOnly = false OR v.available = true) AND " +
            "(LOWER(v.brand) LIKE LOWER(CONCAT('%', :brand, '%')) OR v.brand = '') AND " +
            "(LOWER(v.vehicleName) LIKE LOWER(CONCAT('%', :vehicleName, '%')) OR v.vehicleName = '') AND " +
            "(:vehicleType = 0 OR (:vehicleType = 1 AND TYPE(v) = Car) OR (:vehicleType = 2 AND TYPE(v) = Motorcycle)) " +
            "ORDER BY v.vehicleId")
    Page<Vehicle> findVehicles(boolean availableOnly, String brand, String vehicleName, Integer vehicleType, Pageable pageable);
}
