package com.ec.ecommerceordermanagement.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product entity available for sale in the system
 *
 * <p>Maps to the {@code products} table in the database</p>
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Unique id for the Product
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product
     */
    @Column(nullable = false)
    private String name;

    /**
     * Detailed description of the product
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Price of the product
     *
     * <p>BigDecimal is used to avoid floating-point precision issues
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    /**
     * Category to which product belongs
     */
    @Column(nullable = false, length = 100)
    private String category;

    /**
     * Current status of product
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status;

    /**
     * Timestamp when product was created
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when product was last updated
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Inventory associated with this product
     */
    @OneToOne(mappedBy = "product")
    private Inventory inventory;

    /**
     * Sets creation and update timestamps before the product is persisted
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    /**
     * Updates the modification timestamp before the product is updated
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
