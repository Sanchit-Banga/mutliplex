package com.example.userservice.controller;

import com.example.userservice.dto.Hall;
import com.example.userservice.dto.MoviesDto;
import com.example.userservice.dto.Shows;
import com.example.userservice.model.User;
import com.example.userservice.service.AdminService;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/get-all-users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
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

    @PostMapping("/add-movie")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody MoviesDto moviesDto) {
        String response = adminService.addMovie(moviesDto);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update-movie/{id}")
    public ResponseEntity<Map<String, String>> updateMovie(@RequestBody MoviesDto moviesDto, @PathVariable("id") Long id) {
        String response = adminService.updateMovie(moviesDto, id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable("id") Long id) {
        String response = adminService.deleteMovie(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
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

    @PostMapping("/add-hall")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody Hall hall) {
        String response = adminService.addHall(hall);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/delete-hall/{id}")
    public ResponseEntity<Map<String, String>> deleteHall(@PathVariable("id") Long id) {
        String response = adminService.deleteHall(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/add-show")
    public ResponseEntity<Map<String, String>> addShow(@RequestBody Shows shows) {
        String response = adminService.addShow(shows);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }
}
