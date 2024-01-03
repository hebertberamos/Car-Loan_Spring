package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalReposiory extends JpaRepository<Rental, Long> {
}
