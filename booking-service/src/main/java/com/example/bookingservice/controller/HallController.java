package com.example.bookingservice.controller;

import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.dto.HallDtoResponse;
import com.example.bookingservice.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hall")
@RequiredArgsConstructor
public class HallController {
    final HallService hallService;

    public static final String MSG = "message";

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody HallDtoRequest hallDto) {
        String response = hallService.addHall(hallDto);
        Map<String, String> map = Map.of(MSG, response);
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HallDtoResponse> getHall(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.getHall(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateHall(@RequestBody HallDtoRequest hallDto, @PathVariable("id") Long id) {
        Map<String, String> map = Map.of(MSG, hallService.updateHall(hallDto, id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteHall(@PathVariable("id") Long id) {
        String response = hallService.deleteHall(id);
        Map<String, String> map = Map.of(MSG, response);
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
}
