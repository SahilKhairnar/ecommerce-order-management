package com.ec.ecommerceordermanagement.cart.service;

import com.ec.ecommerceordermanagement.cart.dto.CartResponse;
import com.ec.ecommerceordermanagement.user.entity.User;

/**
 * Defines the business operations related to the cart
 */
public interface CartService {

    /**
     * Creates an empty cart for a user
     *
     * @param user the customer who owns the cart
     */
    void createCart(User user);

    /**
     * Retrieves the cart belonging to the customer
     *
     * @param userId unique identifier of the user
     * @return the customer's cart response
     */
    CartResponse getCart(Long userId);

    /**
     * Adds a product to the customer's cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @param quantity the quantity of the product to add
     * @return the updated cart response
     */
    CartResponse addItem(Long userId, Long productId, Integer quantity);

    /**
     * Updates the quantity of an existing product in the cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @param quantity the new quantity
     * @return the updated cart response
     */
    CartResponse updateItemQuantity(Long userId, Long productId, Integer quantity);

    /**
     * Removes a product from the cart
     *
     * @param userId unique identifier of the user
     * @param productId unique identifier of the product
     * @return the updated cart response
     */
    CartResponse removeItem(Long userId, Long productId);

    /**
     * Removes all the items from the cart
     *
     * @param userId unique identifier of the user
     * @return the updated cart response
     */
    CartResponse clearCart(Long userId);

}
