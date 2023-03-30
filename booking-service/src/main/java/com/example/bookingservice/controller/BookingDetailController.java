package com.example.bookingservice.controller;

import com.example.bookingservice.service.BookingDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking/detail")
@RequiredArgsConstructor
public class BookingDetailController {
    private final BookingDetailService bookingDetailService;

}
