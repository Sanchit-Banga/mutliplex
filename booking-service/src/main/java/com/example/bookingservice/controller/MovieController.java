package com.example.bookingservice.controller;

import com.example.bookingservice.dto.MovieDtoRequest;
import com.example.bookingservice.dto.MovieDtoResponse;
import com.example.bookingservice.model.Movie;
import com.example.bookingservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    final
    MovieService movieService;

    public static final String MSG = "message";

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addMovie(@RequestBody MovieDtoRequest moviedto) {
        String response = movieService.addMovie(moviedto);
        Map<String, String> map = Map.of(MSG, response);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateMovie(@RequestBody MovieDtoRequest movieDtoRequest, @PathVariable("id") Long id) {
        Map<String, String> map = Map.of(MSG, movieService.updateMovie(movieDtoRequest, id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/get/movie/{name}")
    public ResponseEntity<MovieDtoResponse> getMovie(@PathVariable("name") String name) {
        return ResponseEntity.ok(movieService.getMovie(name));
    }

    @GetMapping("/get/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable("id") Long id) {
        Map<String, String> map = Map.of(MSG, movieService.deleteMovie(id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
}
