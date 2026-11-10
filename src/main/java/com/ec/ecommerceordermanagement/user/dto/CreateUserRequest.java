package com.ec.ecommerceordermanagement.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the request payload to create a new user
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    /**
     * Email address of the user
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String Email;

    /**
     * Password of the user
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;

    /**
     * First name of the user
     */
    @NotBlank(message = "First Name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String firstName;

    /**
     * Last name of the user
     */
    @NotBlank(message = "Last Name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    private String lastName;
}
