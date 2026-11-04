package com.ec.ecommerceordermanagement.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents the request payload to create a new product
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequest {

    /**
     * Name of the product
     */
    private String name;

    /**
     * Description of the product
     */
    private String description;

    /**
     * Price of the product
     */
    private BigDecimal price;

    /**
     * Category to which the product belongs
     */
    private String category;
}
