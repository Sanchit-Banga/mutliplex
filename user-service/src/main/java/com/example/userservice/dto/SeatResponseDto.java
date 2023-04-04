package com.example.userservice.dto;

import com.example.userservice.utils.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDto {
    private Integer seatNumber;
    private Double price;
    private SeatType seatType;
}
