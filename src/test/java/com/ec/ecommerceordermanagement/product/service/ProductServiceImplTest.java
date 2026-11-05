package com.ec.ecommerceordermanagement.product.service;

import com.ec.ecommerceordermanagement.common.exception.ResourceNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    /**
     * Tests that an existing product is successfully retrieved by its ID
     *
     * @throws ResourceNotFoundException if the product is not found
     */
    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Mechanical Keyboard");

        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setId(productId);
        expectedResponse.setName("Mechanical Keyboard");

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));
        when(productMapper.toResponse(product))
                .thenReturn(expectedResponse);

        // Act
        ProductResponse actualResponse = productServiceImpl.getProductById(productId);

        // Assert
        assertEquals(expectedResponse,actualResponse);

        // Verify
        verify(productRepository).findById(productId);
        verify(productMapper).toResponse(product);
    }

    /**
     * Tests that retrieving a non-existent product throws ResourceNotFoundException
     */
    @Test
    void getProductById_shouldThrowException_whenProductDoesNotExist(){

        Long productId = 1L;

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        // Act and assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> productServiceImpl.getProductById(productId)
        );

        assertEquals("Product not found with id: 1", exception.getMessage());

        // Verify
        verify(productRepository).findById(productId);
        verify(productMapper, never()).toResponse(any(Product.class));
    }
}
