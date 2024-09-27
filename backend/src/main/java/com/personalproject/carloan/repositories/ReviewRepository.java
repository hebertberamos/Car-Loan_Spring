package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // SQL that take all reviews by a vehicle id

    @Query(value = "SELECT tb_review.* " +
            "FROM tb_vehicle " +
            "INNER JOIN tb_review " +
            "ON tb_vehicle.vehicle_id = tb_review.vehicle_id " +
            "WHERE tb_vehicle.vehicle_id = :vehicleId", nativeQuery = true)
    List<Review> findReviewByVehicle(@Param("vehicleId") Long vehicleId);

}
