package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateInventoryRequest;
import com.ec.ecommerceordermanagement.product.entity.Product;

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

    /**
     * Initializes inventory for a newly created product.
     *
     * @param product product for which inventory should be initialized
     */
    void initializeInventory(Product product);

    /**
     * Updates the available quantity for a product
     *
     * @param productId unique identifier of the product
     * @param request request containing the new available quantity
     * @return updated inventory details
     */
    InventoryResponse updateInventory(Long productId, UpdateInventoryRequest request);
}
