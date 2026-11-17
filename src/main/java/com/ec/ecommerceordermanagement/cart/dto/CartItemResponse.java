package com.ec.ecommerceordermanagement.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents the response returned by the API for the cart item
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    /**
     * Unique identifier of the cart item
     */
    private Long id;

    /**
     * id of the product represented by this item
     */
    private Long productId;

    /**
     * Name of the product
     */
    private String productName;

    /**
     * Price of the product this item represents
     */
    private BigDecimal productPrice;

    /**
     * Quantity of the product
     */
    private Integer quantity;

    /**
     * Total price of the products this cart item contains
     */
    private BigDecimal subtotal;


}
