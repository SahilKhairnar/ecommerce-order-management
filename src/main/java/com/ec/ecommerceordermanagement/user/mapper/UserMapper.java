package com.ec.ecommerceordermanagement.user.mapper;

import com.ec.ecommerceordermanagement.user.dto.CreateUserRequest;
import com.ec.ecommerceordermanagement.user.dto.UserResponse;
import com.ec.ecommerceordermanagement.user.entity.User;
import org.springframework.stereotype.Component;

/**
 * Provides mapping operations between user entities and user DTOs
 */
@Component
public class UserMapper {

    /**
     * Converts a create user request into a user entity
     *
     * @param request request containing user details
     * @return user entity created from the request
     */
    public User toEntity(CreateUserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    /**
     * Converts a user entity into a user response DTO
     *
     * @param user user entity that is to be converted
     * @return user response containing user details
     */
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

}
