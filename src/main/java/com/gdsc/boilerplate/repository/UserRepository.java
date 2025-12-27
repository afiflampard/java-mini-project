package com.gdsc.boilerplate.repository;

import com.gdsc.boilerplate.model.User;
import com.gdsc.boilerplate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.code IS NOT NULL ORDER BY CAST(SUBSTRING(u.code, 4) AS INTEGER) DESC LIMIT 1")
    Optional<User> findTopByOrderByCodeDesc();

    List<User> findAllByRole(Role role);
}
