package com.ec.ecommerceordermanagement.cart.controller;

import com.ec.ecommerceordermanagement.cart.dto.AddCartItemRequest;
import com.ec.ecommerceordermanagement.cart.dto.CartResponse;
import com.ec.ecommerceordermanagement.cart.dto.UpdateCartItemQuantityRequest;
import com.ec.ecommerceordermanagement.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for handling the cart related HTTP requests
 */
@RestController
@RequestMapping("/api/users/{userId}/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Retrieve the cart belonging to a specific user
     *
     * @param userId unique identifier for the user
     * @return the user's cart response
     */
    @GetMapping
    public ResponseEntity<CartResponse> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    /**
     * Adds a product to the user's cart
     *
     * @param userId unique identifier of the user
     * @param request the product and quantity to add
     * @return the updated cart response
     */
    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItem(
            @PathVariable Long userId,
            @Valid @RequestBody AddCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.addItem(userId, request.getProductId(), request.getQuantity())
        );
    }

    /**
     * Updates the quantity of an existing product in the user's cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier for the product
     * @param request the request containing the new quantity
     * @return the updated cart response
     */
    @PutMapping("/items/{productId}")
    public ResponseEntity<CartResponse> updateItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @Valid @RequestBody UpdateCartItemQuantityRequest request) {

        return ResponseEntity.ok(
                cartService.updateItemQuantity(userId, productId, request.getQuantity())
        );
    }

    /**
     * Removes a product from the user's cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @return the updated cart response
     */
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartResponse> removeItem(
            @PathVariable Long userId,
            @PathVariable Long productId) {

        return ResponseEntity.ok(cartService.removeItem(userId, productId));
    }

    /**
     * Removes all items from the user's cart
     * @param userId unique identifier of the user
     * @return the updated cart response
     */
    @DeleteMapping("/items")
    public ResponseEntity<CartResponse> clearCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.clearCart(userId));
    }
}
