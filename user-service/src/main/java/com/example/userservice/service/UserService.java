package com.example.userservice.service;

import com.example.userservice.dto.BookingRequestDto;
import com.example.userservice.dto.BookingResponseDto;
import com.example.userservice.exceptions.AuthException;
import com.example.userservice.exceptions.NotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utils.Role;
import com.example.userservice.utils.UtilityFunctions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private HttpServletRequest request;

    public User registerUser(User userRequest) {
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
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid details. Failed to register");
        }
    }

    public Map<String, String> loginUser(String email, String password) {
        Map<String, String> token = null;
        try {
            User getUser = userRepository.findByEmail(email);
            if (getUser == null) {
                throw new AuthException("Invalid email or password");
            }
            if (!BCrypt.checkpw(password, getUser.getPassword())) throw new AuthException("Invalid email/password");
            token = UtilityFunctions.generateJWTToken(getUser);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthException("Invalid email/password");
        }
        return token;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );
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

    public String addBooking(BookingRequestDto booking) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8089/booking/add")
                    .bodyValue(booking)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            long userId = Long.parseLong(request.getAttribute("userId").toString());
            User user = getUserById(userId);
            Set<String> bookingId = user.getBookingId();
            if (bookingId == null) {
                bookingId = new HashSet<>();
            }
            bookingId.add(response.get("message").toString());
            user.setBookingId(bookingId);
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error at adding booking {}", e.getMessage());
            throw new AuthException("Something went wrong");
        }
        return response.get("message").toString();
    }

    public List<BookingResponseDto> getAllBookings() {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Set<String> bookingIds = getUserById(userId).getBookingId();
        if (bookingIds == null) {
            return Collections.emptyList();
        }
        List<BookingResponseDto> bookingResponseDtos = new ArrayList<>();
        for (String bookingId : bookingIds) {
            bookingResponseDtos.add(getBookingById(bookingId));
        }
        return bookingResponseDtos;
    }

    public BookingResponseDto getBookingById(String bookingId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8089/booking/get/" + bookingId)
                .retrieve()
                .bodyToMono(BookingResponseDto.class)
                .block();
    }
}
