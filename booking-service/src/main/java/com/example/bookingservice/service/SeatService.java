package com.example.bookingservice.service;

import com.example.bookingservice.model.Seat;
import com.example.bookingservice.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {
    final SeatRepository seatRepository;
    public String add(Seat s) {
        seatRepository.save(s);
        return "Seat added successfully";
    }

    public List<Seat> getAllSeat() {
        return seatRepository.findAll();
    }

    public Seat get(Long id) {
        Optional<Seat> obj =seatRepository.findById(id);
        return obj.get();
    }
}
