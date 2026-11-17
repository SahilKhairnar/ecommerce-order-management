package com.ec.ecommerceordermanagement.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the request payload to add item to the cart
 */
@Getter
@Setter
@NoArgsConstructor
public class AddCartItemRequest {

    /**
     * Unique identifier of the product to be added to the cart
     */
    @NotNull(message = "Product id is required")
    @Positive(message = "Product id should be greater then zero")
    private Long productId;

    /**
     * Quantity of the items to be added
     */
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;
}
