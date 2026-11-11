package com.ec.ecommerceordermanagement.product.controller;

import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateInventoryRequest;
import com.ec.ecommerceordermanagement.product.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing product inventory
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Retrieves inventory details for a specific product
     *
     * @param productId unique identifier of the product
     * @return inventory details with HTTP 200 status
     */
    @GetMapping("/{productId}/inventory")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getInventoryByProductId(productId)
        );
    }

    /**
     * Updates the available stock quantity for a product.
     *
     * @param productId unique identifier of the product
     * @param request request containing the new available quantity
     * @return updated inventory details with HTTP 200 status
     */
    @PutMapping("/{productId}/inventory")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long productId,
            @Valid @RequestBody UpdateInventoryRequest request) {

        return ResponseEntity.ok(
                inventoryService.updateInventory(productId, request)
        );
    }
}
