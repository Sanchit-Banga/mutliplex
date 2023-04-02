package com.example.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(max = 100)
    private String username;
    @NotNull
    private Long phoneNumber;
    @NotNull
    @Email
    @Size(max = 100)
    @Column(unique = true)
    private String email;
    @Size(min = 8)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @ElementCollection
    @CollectionTable(name = "user_booking_id", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "booking_id")
    private Set<String> bookingId = new HashSet<>();

}
