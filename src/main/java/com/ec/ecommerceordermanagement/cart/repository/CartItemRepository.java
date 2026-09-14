package com.ec.ecommerceordermanagement.cart.repository;

import com.ec.ecommerceordermanagement.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *Repository for accessing and managing CartItem entities
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
