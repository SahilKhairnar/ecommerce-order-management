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

        // Reject request where no fields are provided for update
        if (
                request.getName() == null
                &&request.getDescription() == null
                &&request.getPrice() == null
                &&request.getCategory() == null
        ) {
            throw new IllegalArgumentException("At least one field must be provided for update");
        }

        // Find the existing product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found with id: " + productId
                ));

        // Update only the fields provided in the request
        if (request.getName() != null) product.setName(request.getName());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getCategory() != null) product.setCategory(request.getCategory());

        // Save the updated product
        Product updatedProduct = productRepository.save(product);

        // Convert the updated entity into a response DTO
        return productMapper.toResponse(updatedProduct);
    }

    /**
     * Deactivates an existing product
     *
     * @param productId unique identifier of the product to deactivate
     * @return the deactivated product
     * @throws ResourceNotFoundException if the product does not exist
     */
    @Override
    public ProductResponse deactivateProduct(Long productId) {

        // Find the existing product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product does not exist with id: " + productId
                ));

        // Set the product status to INACTIVE
        product.setStatus(ProductStatus.INACTIVE);

        // Save the updated product
        Product deactivatedProduct = productRepository.save(product);

        // Convert the updated entity into a response DTO
        return productMapper.toResponse(deactivatedProduct);
    }
}
