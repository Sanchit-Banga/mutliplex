package com.example.bookingservice.controller;

import com.example.bookingservice.model.BookingDetail;
import com.example.bookingservice.service.BookingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addBookingDetail(@RequestBody BookingDetail bookingDetail) {
        Map<String, String> map = Map.of("message", bookingDetailService.addBookingDetail(bookingDetail));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(201));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, String>> deleteBookingDetail(@PathVariable("id") Long id) {
        Map<String, String> map = Map.of("message", bookingDetailService.deleteBookingDetail(id));
        return new ResponseEntity<>(map, HttpStatusCode.valueOf(200));
    }
}
