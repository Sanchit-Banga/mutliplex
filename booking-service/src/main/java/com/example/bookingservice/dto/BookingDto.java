package com.example.bookingservice.dto;

import com.example.bookingservice.model.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Show show;
    private Date bookedDate;
    private Date showDate;
}
