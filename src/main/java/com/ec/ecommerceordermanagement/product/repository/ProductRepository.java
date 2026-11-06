package com.ec.ecommerceordermanagement.product.repository;

import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for accessing and managing Product entities
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves all products matching the specified status
     *
     * @param status status of the products to retrieve
     * @return list of products with specified status
     */
    List<Product> findByStatus(ProductStatus status);
}
