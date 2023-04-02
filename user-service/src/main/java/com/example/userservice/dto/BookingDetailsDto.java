package com.example.userservice.dto;

import com.example.userservice.model.Seat;
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
