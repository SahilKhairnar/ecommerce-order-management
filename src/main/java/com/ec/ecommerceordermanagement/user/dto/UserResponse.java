package com.ec.ecommerceordermanagement.user.dto;

import com.ec.ecommerceordermanagement.user.entity.Role;
import com.ec.ecommerceordermanagement.user.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents the response returned by the API for a User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    /**
     * Unique identifier of the user
     */
    private Long id;

    /**
     * Email address of the user
     */
    private String email;

    /**
     * First name of the user
     */
    private String firstName;

    /**
     * Last name of the user
     */
    private String lastName;

    /**
     * Authorization role of the user
     */
    private Role role;

    /**
     * Status of the user account
     */
    private UserStatus status;

    /**
     * Timestamp when the user was created
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp when the user was last updated
     */
    private LocalDateTime updatedAt;
}
