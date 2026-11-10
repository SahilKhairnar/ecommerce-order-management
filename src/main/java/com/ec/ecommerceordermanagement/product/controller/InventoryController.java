package com.ec.ecommerceordermanagement.product.controller;

import com.ec.ecommerceordermanagement.product.dto.InventoryResponse;
import com.ec.ecommerceordermanagement.product.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
