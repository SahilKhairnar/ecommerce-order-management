package com.ec.ecommerceordermanagement.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the response returned by the API for the cart
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    /**
     * Unique identifier of the cart
     */
    private Long id;

    /**
     * Unique identifier of the user this cart belongs to
     */
    private Long userId;

    /**
     * List of cart items this cart has
     */
    private List<CartItemResponse> items;

    /**
     * Total amount of this cart
     */
    private BigDecimal totalAmount;

    /**
     * Timestamp at which this cart was created
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp at which this cart was last updated
     */
    private LocalDateTime updatedAt;
}
