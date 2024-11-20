package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    // pegar todos os alugeis que tem o checkin para o instante atual ou que já passou e está o atributo running como falso.
    @Query("SELECT r FROM Rental r WHERE r.checkin BETWEEN :minus AND :plus AND r.running = false")
    List<Rental> findRentalsByCheckinDateTime(
            @Param("plus") Instant plus,
            @Param("minus") Instant minus);


    @Query("SELECT r FROM Rental r WHERE FUNCTION('DATE', r.checkout) = CURRENT_DATE")
    Page<Rental> findAllFinishToday(Pageable pageable);
}
