package com.ec.ecommerceordermanagement.user.entity;

import com.ec.ecommerceordermanagement.cart.entity.Cart;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a customer or administrator in the system
 *
 * <p>Maps to the {@code users} table in the database</p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Unique email address used for authentication
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Hashed password
     */
    @Column(name = "password_hash", nullable = false)
    private String password;

    /**
     * First name
     */
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * Indicates user's authorization level
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    /**
     * Indicates user's account status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    /**
     * Timestamp when the account was created
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp when the user was last updated
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Shopping cart associated with this user
     */
    @OneToOne(mappedBy = "user")
    private Cart cart;

    /**
     * Sets creation and update timestamps before the user is persisted
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    /**
     * Updates the modification timestamp before user is updated
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
