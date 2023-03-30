package com.example.bookingservice.dto;

import com.example.bookingservice.utils.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private Integer seatNumber;
    private Double price;
    private Boolean isBooked;
    private SeatType seatType;
}
