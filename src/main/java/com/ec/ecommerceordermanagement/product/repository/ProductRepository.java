package com.ec.ecommerceordermanagement.product.repository;

import com.ec.ecommerceordermanagement.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for accessing and managing Product entities
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
