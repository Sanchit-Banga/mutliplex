package com.example.bookingservice.controller;

import com.example.bookingservice.dto.ShowRequestDto;
import com.example.bookingservice.dto.ShowResponseDto;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/show")
public class ShowController {
    final ShowService showService;

    public static final String MSG = "message";

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addShow(@RequestBody ShowRequestDto show) {
        String response = showService.addShow(show);
        Map<String, String> map = Map.of(MSG, response);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateShow(@RequestBody ShowRequestDto show, @PathVariable("id") Long id) {
        Map<String, String> map = Map.of(MSG, showService.updateShow(show, id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ShowResponseDto>> getShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ShowResponseDto> getShow() {
        return ResponseEntity.ok(showService.getShow());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteShow(@PathVariable("id") Long id) {
        Map<String, String> map = Map.of(MSG, showService.deleteShow(id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
}
