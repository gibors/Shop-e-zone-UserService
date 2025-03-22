package com.shopezone.userservice.service;

import com.shopezone.userservice.dto.request.RegistrationRequest;
import com.shopezone.userservice.entity.User;
import com.shopezone.userservice.entity.UserRole;
import com.shopezone.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void registerUser(RegistrationRequest registrationRequest) {
        try {
            User user = createUserFromDto(registrationRequest);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error saving user");
        }
    }

    private User createUserFromDto(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        // Validate and set role
        String role = registrationRequest.getRole();
        if (Objects.isNull(role)) {
            user.setRole(UserRole.USER);
        } else {
            if (UserRole.isValidRole(role)) {
                user.setRole(UserRole.valueOf(role.toUpperCase()));
            } else {
                throw new IllegalArgumentException("Invalid role: " + role);
            }
        }
        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setLastLogin(now);
        return user;
    }

    public Optional<User> getUserIdByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateLastLogin(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            user.get().setLastLogin(new Date());
            userRepository.save(user.get());
        }
    }
}
