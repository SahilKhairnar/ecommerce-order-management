package com.ec.ecommerceordermanagement.product.controller;

import com.ec.ecommerceordermanagement.product.dto.CreateProductRequest;
import com.ec.ecommerceordermanagement.product.dto.ProductResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateProductRequest;
import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for handling product-related HTTP requests
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Creates a new product
     *
     * @param request product details
     * @return response containing the created product with HTTP status 201
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves a product by its ID
     *
     * @param id unique identifier of the product
     * @return response containing the requested product
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all active products
     *
     * @return response containing a list of all active products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> responses = productService.getAllProducts();
        return ResponseEntity.ok(responses);
    }

    /**
     * Updates an existing product
     *
     * @param id unique identifier of the product to update
     * @param request fields to be updated
     * @return response containing the updated product
     */
    @PutMapping("/{id")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Deactivates an existing product
     *
     * @param id unique identifier of the product to deactivate
     * @return response containing the deactivated product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deactivateProduct(@PathVariable Long id) {
        ProductResponse response = productService.deactivateProduct(id);
        return ResponseEntity.ok(response);
    }
}
