package com.ec.ecommerceordermanagement.order.repository;

import com.ec.ecommerceordermanagement.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing and managing OrderItem entities
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
