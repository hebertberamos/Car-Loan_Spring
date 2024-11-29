package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT r FROM Rental r WHERE r.checkin BETWEEN :minus AND :plus AND r.running = false")
    List<Rental> findRentalsByCheckinDateTime(
            @Param("plus") Instant plus,
            @Param("minus") Instant minus);


    @Query("SELECT r FROM Rental r WHERE " +
            "(:finishTodayOnly = false OR (FUNCTION('DATE', r.checkout) = CURRENT_DATE)) AND " +
            "(:alreadyFinishedOnly = false OR r.running = false AND r.refundMoment IS NOT NULL) AND " +
            "(:runningOnly = false OR r.running = true AND r.refundMoment IS NULL) AND " +
            "(:checkinDate IS NULL AND :endDate IS NULL OR FUNCTION('DATE', r.checkin) = :checkinDate OR (:checkinDate IS NOT NULL AND :endDate IS NOT NULL AND FUNCTION('DATE', r.checkin) BETWEEN :checkinDate AND :endDate)) " +
            "ORDER BY r.id")
    Page<Rental> findRentals(boolean finishTodayOnly, boolean alreadyFinishedOnly, boolean runningOnly, LocalDate checkinDate, LocalDate endDate, Pageable pageable);
}
