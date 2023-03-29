package com.example.userservice.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String username;
    private Long phoneNumber;
    private String email;
    private Set<String> bookingId = new HashSet<>();
}
