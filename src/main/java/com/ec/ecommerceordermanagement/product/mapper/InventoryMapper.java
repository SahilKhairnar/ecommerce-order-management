package com.ec.ecommerceordermanagement.product.mapper;

import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;
import com.ec.ecommerceordermanagement.product.entity.Inventory;
import org.springframework.stereotype.Component;

/**
 * Provides mapping operations between inventory entities and inventory DTOs
 */
@Component
public class InventoryMapper {

    /**
     * Converts an Inventory entity into an InventoryResponse DTO
     *
     * @param inventory inventory entity to convert
     * @return mapped inventory response
     */
    public InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getProductId(),
                inventory.getAvailableQuantity(),
                inventory.getReservedQuantity(),
                inventory.getUpdatedAt()
        );
    }
}
