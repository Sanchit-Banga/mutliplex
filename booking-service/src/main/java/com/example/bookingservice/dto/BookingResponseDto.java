package com.example.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
    private Long bookingId;
    private List<SeatResponseDto> seats;
    private double totalPrice;
    private Date bookedDate;
    private Date showDate;
    private ShowResponseDto show;
}
