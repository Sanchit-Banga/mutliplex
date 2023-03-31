package com.example.bookingservice.dto;

import com.example.bookingservice.model.Show;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long id;
    private Show show;
    private Date bookedDate;
    private Date showDate;
    private UUID bookingUuid;
}
