package com.ec.ecommerceordermanagement.cart.entity;

import com.ec.ecommerceordermanagement.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a product and its quantity inside a cart
 *
 * <p>Maps to the {@code cart_items} table in the database</p>
 */
@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_cart_product",
                        columnNames = {"cart_id", "product_id"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    /**
     * Unique identifier for the cart item
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Cart that contains this item
     */
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    /**
     * Product represented by this item
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Number of units of this product in the cart
     */
    @Column(nullable = false)
    private Integer quantity;
}
