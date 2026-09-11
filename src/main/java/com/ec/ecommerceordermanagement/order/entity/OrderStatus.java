package com.ec.ecommerceordermanagement.order.entity;

/**
 * Defines the state of an order
 */
public enum OrderStatus {
    /**
     * Order has been successfully placed by the customer
     */
    PLACED,

    /**
     * Order has been confirmed for processing
     */
    CONFIRMED,

    /**
     * Order has been canceled
     */
    CANCELLED
}
