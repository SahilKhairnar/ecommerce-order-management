package com.ec.ecommerceordermanagement.product.mapper;

import com.ec.ecommerceordermanagement.product.dto.CreateProductRequest;
import com.ec.ecommerceordermanagement.product.dto.ProductResponse;
import com.ec.ecommerceordermanagement.product.dto.UpdateProductRequest;
import com.ec.ecommerceordermanagement.product.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Provides mapping operations between product entities and product DTOs
 */
@Component
public class ProductMapper {

    /**
     * Converts a create product request into a product entity
     *
     * @param request request containing the product details
     * @return product entity created from the request
     */
    public Product toEntity(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category(request.getCategory())
                .build();
    }

    /**
     * Converts a product entity into a product response DTO
     *
     * @param product product entity to convert
     * @return product response containing the product details
     */
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    /**
     * Updates the supplied fields of an existing product entity
     *
     * <p>Only non-null fields from the request are applied to the entity
     * Fields that are null remain unchanged</p>
     *
     * @param request request containing the fields to update
     * @param product existing product entity to update
     */
    public void updateEntity(UpdateProductRequest request, Product product) {
        if (request.getName() != null) {
            product.setName(request.getName());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getCategory() != null) {
            product.setCategory(request.getCategory());
        }
    }

}
