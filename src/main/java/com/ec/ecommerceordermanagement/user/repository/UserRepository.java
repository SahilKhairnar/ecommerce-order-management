package com.ec.ecommerceordermanagement.user.repository;

import com.ec.ecommerceordermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for accessing and managing User entities
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address
     *
     * @param email user's email address
     * @return the user if found
     */
    Optional<User> findByEmail(String email);
}
