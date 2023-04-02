package com.example.userservice.controller;

import com.example.userservice.dto.*;
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

    @GetMapping("/get/users")
    ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/movie")
    public ResponseEntity<List<MovieDtoResponse>> getAllMovies() {
        return ResponseEntity.ok(adminService.getAllMovies());
    }

    @GetMapping("/get/movie/name/{name}")
    public ResponseEntity<MovieDtoResponse> getMovieByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(adminService.getMovieByName(name));
    }

    @PostMapping("/movie/add")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody MovieDtoResponse moviesDtoResponse) {
        String response = adminService.addMovie(moviesDtoResponse);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/movie/update/{id}")
    public ResponseEntity<Map<String, String>> updateMovie(@RequestBody MovieDtoRequest moviesDtoRequest, @PathVariable("id") Long id) {
        String response = adminService.updateMovie(moviesDtoRequest, id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/movie/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable("id") Long id) {
        String response = adminService.deleteMovie(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/hall")
    public ResponseEntity<List<HallDtoResponse>> getAllHalls() {
        return ResponseEntity.ok(adminService.getAllHalls());
    }

    @GetMapping("/get/hall/{id}")
    public ResponseEntity<HallDtoResponse> getHallById(@PathVariable("id") Long id) {
        HallDtoResponse response = adminService.getHallById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/hall/add")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody HallDtoRequest hall) {
        String response = adminService.addHall(hall);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/hall/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteHall(@PathVariable("id") Long id) {
        String response = adminService.deleteHall(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/show")
    public ResponseEntity<List<ShowResponseDto>> getAllShows() {
        return ResponseEntity.ok(adminService.getAllShows());
    }

    @GetMapping("/get/show/{id}")
    public ResponseEntity<ShowResponseDto> getShowById(@PathVariable("id") Long id) {
        ShowResponseDto response = adminService.getShowById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/show/add")
    public ResponseEntity<Map<String, String>> addShow(@RequestBody ShowRequestDto show) {
        String response = adminService.addShow(show);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }


    @DeleteMapping("/show/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteShow(@PathVariable("id") Long id) {
        String response = adminService.deleteShow(id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/show/update/{id}")
    public ResponseEntity<Map<String, String>> updateShow(@RequestBody ShowRequestDto show, @PathVariable("id") Long id) {
        String response = adminService.updateShow(show, id);
        Map<String, String> map = Map.of("message", response);
        return ResponseEntity.ok(map);
    }
}
