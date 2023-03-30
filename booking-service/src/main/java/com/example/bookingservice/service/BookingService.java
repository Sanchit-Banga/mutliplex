package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking getBookingById(Long id)
    {
       Optional<Booking> optional= bookingRepository.findById(id);
        return optional.get();
    }

    public List<Booking> getBookingAll() {
        return bookingRepository.findAll();
    }
}
