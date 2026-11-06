package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.common.exception.ResourceNotFoundException;
import com.ec.ecommerceordermanagement.product.dto.CreateProductRequest;
import com.ec.ecommerceordermanagement.product.dto.ProductResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateProductRequest;
import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.entity.ProductStatus;
import com.ec.ecommerceordermanagement.product.mapper.ProductMapper;
import com.ec.ecommerceordermanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides business logic for product related operations
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Creates a new product
     *
     * @param request request containing the product details
     * @return the created product
     */
    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = productMapper.toEntity(request);
        product.setStatus(ProductStatus.ACTIVE);
        Product newProduct = productRepository.save(product);
        return productMapper.toResponse(newProduct);
    }

    /**
     * Retrieves a product by its unique identifier
     *
     * @param productId unique identifier of the product
     * @return the requested product
     */
    @Override
    public ProductResponse getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + productId
                ));
        return productMapper.toResponse(product);
    }

    /**
     * Retrieves all active products
     *
     * @return list of active products
     */
    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findByStatus(ProductStatus.ACTIVE)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    /**
     * Updates an existing product
     *
     * @param productId unique identifier of the product to update
     * @param request request containing the fields to update
     * @return the updated product
     */
    @Override
    public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        return null;
    }

    /**
     * Deactivates an existing product
     *
     * @param productId unique identifier of the product to deactivate
     * @return the deactivated product
     */
    @Override
    public ProductResponse deactivateProduct(Long productId) {
        return null;
    }
}
