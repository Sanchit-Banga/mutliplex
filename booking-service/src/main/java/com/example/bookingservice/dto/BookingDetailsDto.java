package com.example.bookingservice.dto;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsDto {
    private Booking booking;
    private Seat seat;
    private Integer numberOfSeats;
}
