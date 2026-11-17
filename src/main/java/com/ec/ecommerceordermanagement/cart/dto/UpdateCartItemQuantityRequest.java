package com.ec.ecommerceordermanagement.cart.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the request payload to update the cart item quantity
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateCartItemQuantityRequest {

    /**
     * New quantity for the cart item
     */
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
}
