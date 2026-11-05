package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.product.dto.CreateProductRequest;
import com.ec.ecommerceordermanagement.product.dto.ProductResponse;
import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.entity.ProductStatus;
import com.ec.ecommerceordermanagement.product.mapper.ProductMapper;
import com.ec.ecommerceordermanagement.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    /**
     * Verifies that a product is created successfully with an active status
     */
    @Test
    void createProduct_shouldCreateActiveProduct() {

        CreateProductRequest request = new CreateProductRequest();

        Product product = new Product();
        product.setName("Mechanical Keyboard");
        product.setDescription("Wireless Mechanical Keyboard");
        product.setPrice(new BigDecimal("999.99"));
        product.setCategory("Electronics");

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Mechanical Keyboard");
        savedProduct.setDescription("Wireless Mechanical Keyboard");
        savedProduct.setPrice(new BigDecimal("999.99"));
        savedProduct.setCategory("Electronics");
        savedProduct.setStatus(ProductStatus.ACTIVE);

        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setId(1L);
        expectedResponse.setName("Mechanical Keyboard");
        expectedResponse.setDescription("Wireless Mechanical Keyboard");
        expectedResponse.setPrice(new BigDecimal("999.99"));
        expectedResponse.setCategory("Electronics");
        expectedResponse.setStatus(ProductStatus.ACTIVE);

        // Mock mapper: request DTO -> entity
        when(productMapper.toEntity(request))
                .thenReturn(product);

        // Mock repository: save entity -> saved entity
        when(productRepository.save(any(Product.class)))
                .thenReturn(savedProduct);

        // Mock mapper: saved entity -> response DTO
        when(productMapper.toResponse(savedProduct))
                .thenReturn(expectedResponse);

        // Act
        ProductResponse actualResponse = productServiceImpl.createProduct(request);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        assertEquals(ProductStatus.ACTIVE, savedProduct.getStatus());
        assertEquals(1L, savedProduct.getId());
        assertEquals("Mechanical Keyboard", savedProduct.getName());
        assertEquals("Wireless Mechanical Keyboard", savedProduct.getDescription());
        assertEquals(new BigDecimal("999.99"), savedProduct.getPrice());
        assertEquals("Electronics", savedProduct.getCategory());

        // Verify interactions

        verify(productMapper).toEntity(request);
        verify(productRepository).save(product);
        verify(productMapper).toResponse(savedProduct);
    }
}
