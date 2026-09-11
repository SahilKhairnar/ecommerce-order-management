package com.ec.ecommerceordermanagement.user.entity;

/**
 * Defines the authorization roles in the application
 */
public enum Role {

    /**
     * Standard customer who can browse products, manage cart and place orders
     */
    CUSTOMER,

    /**
     * Administrator who can manage products and inventory
     */
    ADMIN
}
