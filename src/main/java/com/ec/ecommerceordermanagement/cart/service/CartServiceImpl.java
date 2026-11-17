package com.ec.ecommerceordermanagement.cart.service;

import com.ec.ecommerceordermanagement.cart.dto.CartResponse;
import com.ec.ecommerceordermanagement.cart.entity.Cart;
import com.ec.ecommerceordermanagement.cart.entity.CartItem;
import com.ec.ecommerceordermanagement.cart.mapper.CartMapper;
import com.ec.ecommerceordermanagement.cart.repository.CartItemRepository;
import com.ec.ecommerceordermanagement.cart.repository.CartRepository;
import com.ec.ecommerceordermanagement.common.exception.ResourceNotFoundException;
import com.ec.ecommerceordermanagement.product.entity.Product;
import com.ec.ecommerceordermanagement.product.service.InventoryService;
import com.ec.ecommerceordermanagement.product.service.ProductService;
import com.ec.ecommerceordermanagement.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides business logic for cart related operations
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final CartMapper cartMapper;

    /**
     * Creates an empty cart for a user
     *
     * @param user the customer who owns the cart
     */
    @Override
    public void createCart(User user) {

        Cart cart = Cart.builder()
                .user(user)
                .build();

        cartRepository.save(cart);
    }

    /**
     * Retrieves the cart belonging to the customer
     *
     * @param userId unique identifier of the user
     * @return the customer's cart response
     */
    @Transactional(readOnly = true)
    @Override
    public CartResponse getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart not found for the user id: " + userId
                ));

        return cartMapper.toCartResponse(cart);
    }

    /**
     * Adds a product to the customer's cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @param quantity the quantity of the product to add
     * @return the updated cart response
     */
    @Transactional
    @Override
    public CartResponse addItem(Long userId, Long productId, Integer quantity) {

        // Retrieve the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart not found for the user id: " + userId
                ));

        // Retrieve the active product
        Product product = productService.getProductEntityById(productId);

        // Find if this product already exists in the cart
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).orElse(null);

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        // Calculate the final quantity
        int finalQuantity = quantity;

        if (cartItem != null) {
            finalQuantity += cartItem.getQuantity();
        }

        // Check whether sufficient inventory is available
        if(!inventoryService.hasSufficientStock(productId, finalQuantity)) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        if (cartItem == null) {
            // Create a new cart item
            cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(finalQuantity)
                    .build();

            cart.getItems().add(cartItem);

        } else {
            // Increase the quantity of the existing cart item
            cartItem.setQuantity(finalQuantity);
        }

        cartItemRepository.save(cartItem);

        return cartMapper.toCartResponse(cart);
    }

    /**
     * Updates the quantity of an existing product in the cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @param quantity the new quantity
     * @return the updated cart response
     */
    @Transactional
    @Override
    public CartResponse updateItemQuantity(Long userId, Long productId, Integer quantity) {

        // Validate the requesting quantity
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        // Retrieve the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart not found for the user id: " + userId
                ));

        // Find the existing cart item
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found in the cart"
        ));

        // Check whether sufficient inventory is available
        if (!inventoryService.hasSufficientStock(productId, quantity)) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        // Update the cart item's quantity
        cartItem.setQuantity(quantity);

        // Save the updated cart item
        cartItemRepository.save(cartItem);

        // Return the updated cart response
        return cartMapper.toCartResponse(cart);
    }

    /**
     * Removes a product from the cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @return the updated cart response
     */
    @Transactional
    @Override
    public CartResponse removeItem(Long userId, Long productId) {

        // Retrieve the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart not found for the user id: " + userId
                ));

        // Find the cart item to remove
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found in the user's cart"
                ));

        // Remove the item from the cart's in-memory collection
        cart.getItems().remove(cartItem);

        // Delete the item from the database
        cartItemRepository.delete(cartItem);

        return cartMapper.toCartResponse(cart);
    }

    /**
     * Removes all the items from the cart
     *
     * @param userId unique identifier of the user
     * @return the updated cart response
     */
    @Transactional
    @Override
    public CartResponse clearCart(Long userId) {

        // Retrieve the user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart not found for the user id: " + userId
                ));

        // Delete all cart items from the database
        cartItemRepository.deleteAll(cart.getItems());

        // Clear the in-memory collection
        cart.getItems().clear();

        // Return the updated cart response
        return cartMapper.toCartResponse(cart);
    }
}
