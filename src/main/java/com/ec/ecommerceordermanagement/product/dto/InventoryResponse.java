package com.ec.ecommerceordermanagement.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents the response returned by the API for the inventory
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    /**
     * Unique identifier of the product whose inventory is to be checked
     */
    private Long productId;

    /**
     * Available quantity of the product in the inventory
     */
    private Integer availableQuantity;

    /**
     * Reserved quantity of the product in the inventory
     */
    private Integer reservedQuantity;

    /**
     * Date and time when the inventory was last updated
     */
    private LocalDateTime updatedAt;
}
