package com.ec.ecommerceordermanagement.cart.entity;

import com.ec.ecommerceordermanagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the shopping cart of a customer
 *
 * <p>Maps to the {@code carts} table in the database</p>
 *
 * <p>Each customer can have one active cart</p>
 */
@Entity
@Table(name = "carts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    /**
     * Unique identifier for the cart
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identifier to the customer who owns this cart
     *
     * <p>The database enforces uniqueness so that one
     * customer has at most one cart</p>
     */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /**
     * Items currently contained in this cart
     */
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    /**
     * Timestamp when the cart was created
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the cart was last updated
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Sets creation and update timestamps before the cart is persisted
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    /**
     * Updates the modification timestamp before the cart is updated
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
