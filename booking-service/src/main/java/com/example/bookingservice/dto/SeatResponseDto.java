package com.example.bookingservice.dto;

import com.example.bookingservice.utils.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDto {
    private Integer seatNumber;
    private Double price;
    private SeatType seatType;
}
