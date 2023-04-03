package com.example.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {
    private Long showId;
    private Date bookedDate;
    private Date showDate;
    private BookingDetailsDto bookingDetails;
}
