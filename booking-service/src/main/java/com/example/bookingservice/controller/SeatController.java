package com.example.bookingservice.controller;

import com.example.bookingservice.model.Seat;
import com.example.bookingservice.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;
    @GetMapping("/get/{id}")
    public ResponseEntity<Seat> get(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(seatService.get(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<Seat>> getAllSeat(){
        return ResponseEntity.ok(seatService.getAllSeat());
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> add(@RequestBody Seat s)
    {
        Map<String,String> map = Map.of("message",seatService.add(s));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }
}
