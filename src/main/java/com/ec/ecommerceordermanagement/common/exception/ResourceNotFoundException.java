package com.ec.ecommerceordermanagement.common.exception;

/**
 * Exception thrown when a requested resource cannot be found
 */
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Constructs an exception with the specified message
     *
     * @param message description of the resource that was not found
     */
    public ResourceNotFoundException(String message){
        super(message);
    }
}
