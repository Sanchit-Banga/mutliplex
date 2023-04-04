package com.example.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetailsDto {
    private List<SeatRequestDto> seat;
    private Integer numberOfSeats;
}
