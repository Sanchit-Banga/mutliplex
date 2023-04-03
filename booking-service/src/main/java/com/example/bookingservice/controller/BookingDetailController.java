package com.example.bookingservice.controller;


import com.example.bookingservice.model.BookingDetail;
import com.example.bookingservice.service.BookingDetailService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking/detail")
@RequiredArgsConstructor
public class BookingDetailController {
    private final BookingDetailService bookingDetailService;

    @GetMapping("get/all")
    public ResponseEntity<List<BookingDetail>> getAllBookingDetails() {
        return ResponseEntity.ok(bookingDetailService.getAllBookingDetails());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<BookingDetail> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingDetailService.getById(id));
    }

}
