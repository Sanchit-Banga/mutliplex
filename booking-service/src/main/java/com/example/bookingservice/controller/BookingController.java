package com.example.bookingservice.controller;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long id)
    {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<Booking>> getBookingAll()
    {
        return ResponseEntity.ok(bookingService.getBookingAll());
    }
}
