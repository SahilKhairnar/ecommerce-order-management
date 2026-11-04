package com.ec.ecommerceordermanagement.product.dto;

import com.ec.ecommerceordermanagement.product.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents the response returned by the API for a product
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    /**
     * Unique identifier of the product
     */
    private Long id;

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

    /**
     * Current status of the product
     */
    private ProductStatus status;

    /**
     * Date and time when the product was created
     */
    private LocalDateTime createdAt;

    /**
     * Date and time when the product was last updated
     */
    private LocalDateTime updatedAt;
}
