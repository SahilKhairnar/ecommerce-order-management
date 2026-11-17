package com.ec.ecommerceordermanagement.cart.repository;

import com.ec.ecommerceordermanagement.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 *Repository for accessing and managing CartItem entities
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Finds a cart item for a specific product within a cart.
     *
     * @param cartId the cart's ID
     * @param productId the product's ID
     * @return the cart item if found
     */
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
