package com.ec.ecommerceordermanagement.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents the request payload to update the product
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest {
    /**
     * Updated name of the product
     */
    @Size(min = 1, max = 255)
    private String name;

    /**
     * Updated description of the product
     */
    @Size(min = 1, max = 255)
    private String description;

    /**
     * Updated price of the product
     */
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    /**
     * Updated category of the product
     */
    @Size(min = 1, max = 100)
    private String category;
}
