package com.ec.ecommerceordermanagement.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents error response returned by the REST API
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * HTTP status code of the error
     */
    private int status;

    /**
     * HTTP error description
     */
    private String error;

    /**
     * Error message
     */
    private String message;

    /**
     * Timestamp indicating when the error occurred
     */
    private LocalDateTime timestamp;
}
