package com.ec.ecommerceordermanagement.order.entity;

import com.ec.ecommerceordermanagement.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Represents a product included in an order
 *
 * <p>Maps to the {@code order_items} table in the database</p>
 *
 * <p>The Product name and unit price are stored as snapshots so historical
 * orders remain unchanged when the product is updated later</p>
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    /**
     * Unique identifier for the order item
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identifier of the order containing this item
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * Identifier of the product that was purchased
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * Product name captured at the time the order was placed
     */
    @Column(name = "product_name", nullable = false)
    private String productName;

    /**
     * Product price captured at the time the order was placed
     */
    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    /**
     * Number of units purchased.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Total price for this order item.
     *
     * <p>Calculated as unit price multiplied by quantity.</p>
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal subtotal;
}
