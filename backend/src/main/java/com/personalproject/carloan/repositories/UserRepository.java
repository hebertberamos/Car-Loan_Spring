package com.personalproject.carloan.repositories;

import com.personalproject.carloan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByCpf(String cpf);
}
