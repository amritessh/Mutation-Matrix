package com.mutationmatrix.user_management_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.user_management_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
