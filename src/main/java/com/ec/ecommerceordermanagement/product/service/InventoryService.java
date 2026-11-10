package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;

/**
 * Defines business operations related to product inventory
 */
public interface InventoryService {

    /**
     * Retrieves inventory details for a specific product
     *
     * @param productId unique identifier of the product
     * @return inventory details for the product
     */
    InventoryResponse getInventoryByProductId(Long productId);

    /**
     * Checks whether sufficient stock is available for a requested quantity
     *
     * @param productId unique identifier of the product
     * @param quantity requested quantity
     * @return true if the sufficient stock os available, otherwise false
     */
    boolean hasSufficientStock(Long productId, Integer quantity);
}
