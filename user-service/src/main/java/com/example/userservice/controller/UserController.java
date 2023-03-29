package com.example.userservice.controller;

import com.example.userservice.dto.Hall;
import com.example.userservice.dto.MoviesDto;
import com.example.userservice.dto.Shows;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.model.User;
import com.example.userservice.service.AdminService;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.UtilityFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdminService adminService;

    @PostMapping("/register")
    ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRequest userRequest) {
        User userNew = userService.registerUser(userRequest);
        return new ResponseEntity<>(UtilityFunctions.generateJWTToken(userNew), HttpStatusCode.valueOf(201));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Map<String, String>> updateUser(@RequestBody User user, @PathVariable Long id) {
        Map<String, String> map = Map.of("message", userService.updateUser(user, id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @PostMapping("/login")
    ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(UtilityFunctions.generateJWTToken(user));
    }

    @GetMapping("/get-all-shows")
    ResponseEntity<List<Shows>> getAllShows() {
        return ResponseEntity.ok(userService.getAllShows());
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<MoviesDto>> getAllMovies() {
        return ResponseEntity.ok(adminService.getAllMovies());
    }

    @GetMapping("/get-movie-by-id/{id}")
    public ResponseEntity<Map<String, String>> getMovieById(@PathVariable("id") Long id) {
        String response = adminService.getMovieById(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<List<MoviesDto>> getMovieByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(adminService.getMovieByName(name));
    }

    @GetMapping("/get-all-halls")
    public ResponseEntity<List<Hall>> getAllHalls() {
        return ResponseEntity.ok(adminService.getAllHalls());
    }

    @GetMapping("/get-hall-by-id/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable("id") Long id) {
        Hall response = adminService.getHallById(id);
        return ResponseEntity.ok(response);
    }
}
