package com.ec.ecommerceordermanagement.user.service;

import com.ec.ecommerceordermanagement.user.dto.CreateUserRequest;
import com.ec.ecommerceordermanagement.user.dto.UserResponse;

/**
 * Defines business operations related to the users
 */
public interface UserService {

    /**
     * Registers a new user
     *
     * @param request request containing user details
     * @return the registered user
     */
    UserResponse registerUser(CreateUserRequest request);

    /**
     * Retrieves a user by their unique identifier
     *
     * @param userId unique user identifier
     * @return the requested user's details
     */
    UserResponse getUserById(Long userId);
}
