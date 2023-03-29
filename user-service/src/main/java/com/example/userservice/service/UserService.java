package com.example.userservice.service;

import com.example.userservice.dto.Shows;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.exceptions.AuthException;
import com.example.userservice.exceptions.NotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utils.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;

//    private final EmailSenderService emailSenderService;

    public User registerUser(UserRequest userRequest) {
        try {
            if (userRepository.findByEmail(userRequest.getEmail()) != null) {
                throw new AuthException("This email already exists");
            }
            String hashedPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt(10));
            User user = User.builder()
                    .username(userRequest.getUsername())
                    .email(userRequest.getEmail())
                    .phoneNumber(userRequest.getPhoneNumber())
                    .role(Role.USER.toString())
                    .password(hashedPassword)
                    .build();
            userRepository.save(user);
//            emailSenderService.sendEmail(user.getEmail(), "Welcome to Movie Booking App", "You have successfully registered");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid details. Failed to register");
        }
    }

    public void loginUser(String email, String password) {
        try {
            User getUser = userRepository.findByEmail(email);
            if (getUser == null) {
                throw new AuthException("Invalid email or password");
            }
            if (!BCrypt.checkpw(password, getUser.getPassword())) throw new AuthException("Invalid email/password");
        } catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid email/password");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String updateUser(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User does not exist found");
        }
        User user1 = userOptional.get();
        if (user1.getRole().equalsIgnoreCase("ADMIN") && user.getRole().equalsIgnoreCase("USER")) {
            throw new AuthException("You cannot update admin user");
        }
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return "User updated successfully";
    }

    public List<Shows> getAllShows() {
        List<Shows> response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/shows/getallshows")
                    .retrieve()
                    .bodyToFlux(Shows.class)
                    .collectList()
                    .block();
            log.info("Response from '/api/v1/users/getAllShows': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all shows: {}", e.getMessage());
            return Collections.emptyList();
        }
        return response;
    }
}
