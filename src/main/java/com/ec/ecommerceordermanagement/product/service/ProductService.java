package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.product.dto.CreateProductRequest;
import com.ec.ecommerceordermanagement.product.dto.ProductResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateProductRequest;

import java.util.List;

/**
 * Defines business operations related to the products
 */
public interface ProductService {

    /**
     * Creates a new product
     *
     * @param request request containing the product details
     * @return the created product
     */
    ProductResponse createProduct(CreateProductRequest request);

    /**
     * Retrieves a product by its unique identifier
     *
     * @param productId unique identifier of the product
     * @return the requested product
     */
    ProductResponse getProductById(Long productId);

    /**
     * Retrieves all active products
     *
     * @return list of active products
     */
    List<ProductResponse> getAllProducts();

    /**
     * Updates an existing product
     *
     * @param productId unique identifier of the product to update
     * @param request request containing the fields to update
     * @return the updated product
     */
    ProductResponse updateProduct(Long productId, UpdateProductRequest request);

    /**
     * Deactivates an existing product
     *
     * @param productId unique identifier of the product to deactivate
     * @return the deactivated product
     */
    ProductResponse deactivateProduct(Long productId);
}
