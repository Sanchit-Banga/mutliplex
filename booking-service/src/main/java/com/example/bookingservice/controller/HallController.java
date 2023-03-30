package com.example.bookingservice.controller;

import com.example.bookingservice.dto.HallDto;
import com.example.bookingservice.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/hall")
@RequiredArgsConstructor
public class HallController {
    final HallService hallService;

    public static final String MSG = "message";

    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addHall(@RequestBody HallDto hallDto){
        String response =hallService.addHall(hallDto);
        Map<String, String> map = Map.of(MSG, response);
        return ResponseEntity.ok(map);
    }

}
