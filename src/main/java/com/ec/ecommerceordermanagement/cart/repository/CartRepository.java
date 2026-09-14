package com.ec.ecommerceordermanagement.cart.repository;

import com.ec.ecommerceordermanagement.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for accessing and managing Cart entities
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Finds a cart belonging to a specific user
     *
     * @param userId user's ID
     * @return the user's cart if found
     */
    Optional<Cart> findByUserId(Long userId);
}
