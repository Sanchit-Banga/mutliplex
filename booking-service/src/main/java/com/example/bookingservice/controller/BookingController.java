package com.example.bookingservice.controller;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.dto.BookingRequestDto;
import com.example.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/get/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<BookingDto>> getBookingAll()
    {
        return ResponseEntity.ok(bookingService.getBookingAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addBooking(@RequestBody BookingRequestDto booking)
    {
        Map<String,String> map = Map.of("message",bookingService.add(booking));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteBooking(@PathVariable("id") Long id)
    {
        Map<String,String> map = Map.of("message",bookingService.deleteBooking(id));
        return new ResponseEntity<>(map,HttpStatusCode.valueOf(200));
    }
}