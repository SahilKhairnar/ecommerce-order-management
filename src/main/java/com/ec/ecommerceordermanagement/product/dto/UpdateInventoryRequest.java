package com.ec.ecommerceordermanagement.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the request to update product inventory
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateInventoryRequest {

    /**
     * New available quantity for the product
     */
    @NotNull(message = "Available quantity is required")
    @PositiveOrZero(message = "Available quantity cannot be negative")
    private Integer availableQuantity;
}
