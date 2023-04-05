package com.example.userservice.controller;

import com.example.userservice.dto.*;
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
    ResponseEntity<Map<String, String>> registerUser(@RequestBody User userRequest) {
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
        Map<String, String> map = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get/movie")
    public ResponseEntity<List<MovieDtoResponse>> getAllMovies() {
        return ResponseEntity.ok(adminService.getAllMovies());
    }


    @GetMapping("/get/movie/name/{name}")
    public ResponseEntity<MovieDtoResponse> getMovieByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(adminService.getMovieByName(name));
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

    @GetMapping("/get/show")
    public ResponseEntity<List<ShowResponseDto>> getAllShows() {
        return ResponseEntity.ok(adminService.getAllShows());
    }

    @GetMapping("/get/show/{id}")
    public ResponseEntity<ShowResponseDto> getShowById(@PathVariable("id") Long id) {
        ShowResponseDto response = adminService.getShowById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/booking/add")
    public ResponseEntity<Map<String, String>> addBooking(@RequestBody BookingRequestDto booking) {
        Map<String, String> map = Map.of("booking id", userService.addBooking(booking));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/booking/get")
    public ResponseEntity<Map<String, List<BookingResponseDto>>> getAllBookings() {
        Map<String, List<BookingResponseDto>> map = Map.of("bookings", userService.getAllBookings());
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
}
