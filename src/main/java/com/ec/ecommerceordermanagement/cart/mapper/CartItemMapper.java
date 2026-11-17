package com.ec.ecommerceordermanagement.cart.mapper;

import com.ec.ecommerceordermanagement.cart.dto.CartItemResponse;
import com.ec.ecommerceordermanagement.cart.entity.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Provides mapping operations between cart item entities and cart item DTOs
 */
@Component
public class CartItemMapper {

    /**
     * Converts a CartItem entity into a CartItemResponse DTO
     *
     * @param cartItem the cart item entity
     * @return the corresponding cart item response
     */
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getProduct().getPrice(),
                cartItem.getQuantity(),
                cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
        );
    }
}
