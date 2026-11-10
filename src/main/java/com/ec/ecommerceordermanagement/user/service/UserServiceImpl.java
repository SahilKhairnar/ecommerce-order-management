package com.ec.ecommerceordermanagement.user.service;

import com.ec.ecommerceordermanagement.common.exception.ResourceNotFoundException;
import com.ec.ecommerceordermanagement.user.dto.CreateUserRequest;
import com.ec.ecommerceordermanagement.user.dto.UserResponse;
import com.ec.ecommerceordermanagement.user.entity.Role;
import com.ec.ecommerceordermanagement.user.entity.User;
import com.ec.ecommerceordermanagement.user.entity.UserStatus;
import com.ec.ecommerceordermanagement.user.mapper.UserMapper;
import com.ec.ecommerceordermanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Provides business logic for user related operations
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user
     *
     * @param request request containing user details
     * @return the registered user
     */
    @Override
    public UserResponse registerUser(CreateUserRequest request) {

        // Check if the user is already registered with this email
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Convert to the user entity
        User user = userMapper.toEntity(request);

        // Encode the password and add it to the user entity
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Set user role and status
        user.setRole(Role.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    /**
     * Retrieves a user from its unique identifier
     *
     * @param userId unique user identifier
     * @return the requested user details
     */
    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + userId
                ));
        return userMapper.toResponse(user);
    }
}
