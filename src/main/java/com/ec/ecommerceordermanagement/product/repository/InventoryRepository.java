package com.ec.ecommerceordermanagement.product.repository;

import com.ec.ecommerceordermanagement.product.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing and managing Inventory entities
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}