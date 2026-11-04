package com.ec.ecommerceordermanagement.product.dto;

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
    private String name;

    /**
     * Updated description of the product
     */
    private String description;

    /**
     * Updated price of the product
     */
    private BigDecimal price;

    /**
     * Updated category of the product
     */
    private String category;
}
