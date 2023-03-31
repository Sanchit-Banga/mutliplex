package com.example.bookingservice.service;

import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Seat;
import com.example.bookingservice.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final HallRepository hallRepository;

    public int getAvailableSeats(Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No such hall found"));
            List<Seat> seats = hall.getSeats();
            int availableSeats = 0;
            availableSeats = (int) seats.stream().filter(seat -> !seat.getIsBooked()).count();
            return availableSeats;
        } catch (Exception e) {
            throw new BadRequestException("No such hall found");
        }
    }

    public void deleteSeat(Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No such hall found"));
            List<Seat> seats = hall.getSeats();
            seats.remove(seats.size() - 1);
            hall.setSeats(seats);
            hallRepository.save(hall);
        } catch (Exception e) {
            throw new BadRequestException("No such hall found");
        }
    }
}
