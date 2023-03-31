package com.example.bookingservice.controller;

import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.dto.HallDtoResponse;
import com.example.bookingservice.model.Seat;
import com.example.bookingservice.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hall")
@RequiredArgsConstructor
public class HallController {
    public static final String msg = "message";
    private final HallService hallService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addHall(@RequestBody HallDtoRequest hallDto) {
        String response = hallService.addHall(hallDto);
        Map<String, String> map = Map.of(msg, response);
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/get")
    public ResponseEntity<List<HallDtoResponse>> getAllHall() {
        return ResponseEntity.ok(hallService.getAllHalls());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HallDtoResponse> getHall(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.getHall(id));
    }

    @GetMapping("/get/{id}/seats")
    public ResponseEntity<List<Seat>> getSeats(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hallService.getAllSeatsOfHall(id));
    }

    @GetMapping("/get/{hall_id}/seats/{seat_id}")
    public ResponseEntity<Seat> getSeats(@PathVariable("hall_id") Long hallId, @PathVariable("seat_id") Long seatId) {
        return ResponseEntity.ok(hallService.getSeatFromHallById(hallId, seatId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updateHall(@RequestBody HallDtoRequest hallDto, @PathVariable("id") Long id) {
        Map<String, String> map = Map.of(msg, hallService.updateHall(hallDto, id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteHall(@PathVariable("id") Long id) {
        String response = hallService.deleteHall(id);
        Map<String, String> map = Map.of(msg, response);
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }

}
