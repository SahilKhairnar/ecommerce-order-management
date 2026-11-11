package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.common.exception.ResourceNotFoundException;
import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;
import com.ec.ecommerceordermanagement.product.entity.Inventory;
import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.mapper.InventoryMapper;
import com.ec.ecommerceordermanagement.product.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implements business operations related to the product inventory
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    /**
     * Retrieves inventory details for a specific product
     *
     * @param productId unique identifier of the product
     * @return inventory details for a product
     */
    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory not found for the product id: " + productId
                ));
        return inventoryMapper.toResponse(inventory);
    }

    /**
     * Checks whether sufficient stock is available for a requested quantity
     *
     * @param productId unique identifier of the product
     * @param quantity requested quantity
     * @return true if sufficient stock is available, otherwise false
     */
    @Override
    public boolean hasSufficientStock(Long productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory not found for the product id: " + productId
                ));
        return inventory.getAvailableQuantity() >= quantity;
    }

    /**
     * Initializes inventory for a newly created product.
     *
     * @param product product for which inventory should be initialized
     */
    @Override
    public void initializeInventory(Product product) {

        Inventory inventory = Inventory.builder()
                .product(product)
                .availableQuantity(0)
                .reservedQuantity(0)
                .build();

        inventoryRepository.save(inventory);
    }
}
