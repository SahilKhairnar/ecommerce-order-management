package com.ec.ecommerceordermanagement.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents the inventory associated with a product
 *
 * <p>Maps to the {@code inventories} table in the database</p>
 * <p>Each product has exactly one inventory record</p>
 */
@Entity
@Table(name = "inventories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    /**
     * Product associated with this inventory
     */
    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Number of units available for sale
     */
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    /**
     * Number of units currently reserved
     */
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    /**
     * Timestamp when the inventory was last updated
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Sets the initial update timestamp before the inventory is persisted
     */
    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the timestamp before the inventory is modified
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
