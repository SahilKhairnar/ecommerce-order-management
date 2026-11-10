package com.ec.ecommerceordermanagement.user.service;

import com.ec.ecommerceordermanagement.user.dto.CreateUserRequest;
import com.ec.ecommerceordermanagement.user.dto.UserResponse;
import com.ec.ecommerceordermanagement.user.entity.Role;
import com.ec.ecommerceordermanagement.user.entity.User;
import com.ec.ecommerceordermanagement.user.entity.UserStatus;
import com.ec.ecommerceordermanagement.user.mapper.UserMapper;
import com.ec.ecommerceordermanagement.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    /**
     * Tests successful user registration.
     */
    @Test
    void registerUser_shouldReturnUserResponse_whenRegistrationIsSuccessful() {

        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setFirstName("FirstName");
        request.setLastName("LastName");

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        User savedUser = User.builder()
                .id(1L)
                .email(request.getEmail())
                .password("encodedPassword")
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.CUSTOMER)
                .status(UserStatus.ACTIVE)
                .build();

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId(1L);
        expectedResponse.setEmail(request.getEmail());
        expectedResponse.setFirstName(request.getFirstName());
        expectedResponse.setLastName(request.getLastName());
        expectedResponse.setRole(Role.CUSTOMER);
        expectedResponse.setStatus(UserStatus.ACTIVE);

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(user);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapper.toResponse(any(User.class))).thenReturn(expectedResponse);

        // Act
        UserResponse actualResponse = userServiceImpl.registerUser(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(1L, actualResponse.getId());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
        assertEquals(expectedResponse.getLastName(), actualResponse.getLastName());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
        assertEquals(expectedResponse.getRole(), actualResponse.getRole());

        //Verify
        verify(userRepository).existsByEmail(request.getEmail());
        verify(userMapper).toEntity(request);
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(any(User.class));
        verify(userMapper).toResponse(savedUser);
    }
}
