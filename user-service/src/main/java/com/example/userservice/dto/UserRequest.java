package com.example.userservice.dto;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String username;
    private Long phoneNumber;
    private String email;
    private String password;
}
