package com.ec.ecommerceordermanagement.cart.mapper;

import com.ec.ecommerceordermanagement.cart.dto.CartItemResponse;
import com.ec.ecommerceordermanagement.cart.dto.CartResponse;
import com.ec.ecommerceordermanagement.cart.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides mapping operations between cart entities and cart DTOs
 */
@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    /**
     * Converts a Cart entity into a CartResponse DTO
     *
     * @param cart the cart entity
     * @return the corresponding cart response
     */
    public CartResponse toCartResponse(Cart cart) {

        List<CartItemResponse> items = cart.getItems()
                .stream()
                .map(cartItemMapper::toCartItemResponse)
                .toList();

        BigDecimal totalAmount = items
                .stream()
                .map(CartItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(
                cart.getId(),
                cart.getUser().getId(),
                items,
                totalAmount,
                cart.getCreatedAt(),
                cart.getUpdatedAt()
        );
    }
}
