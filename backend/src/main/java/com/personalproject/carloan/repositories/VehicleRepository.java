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
            "(LOWER(v.brand) LIKE LOWER(CONCAT ('%',:brand,'%')) OR v.brand = '') AND " +
            "(LOWER(v.name) LIKE LOWER(CONCAT ('%',:name,'%')) OR v.name = '') " +
            "ORDER BY v.id")
    Page<Vehicle> findVehicles(boolean availableOnly, String brand, String name, Pageable pageable);

}
