package com.ec.ecommerceordermanagement.order.repository;

import com.ec.ecommerceordermanagement.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing and managing Order entities
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
