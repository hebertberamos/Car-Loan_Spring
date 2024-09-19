package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Car;
import com.personalproject.carloan.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE " +
            "(:availableOnly = false OR c.available = true) AND " +
            "(LOWER(c.brand) LIKE LOWER(CONCAT ('%',:brand,'%')) OR c.brand = '') AND " +
            "(LOWER(c.vehicleName) LIKE LOWER(CONCAT ('%',:vehicleName,'%')) OR c.vehicleName = '') " +
            "ORDER BY c.id")
    Page<Car> findCars(boolean availableOnly, String brand, String vehicleName, Pageable pageable);
}
