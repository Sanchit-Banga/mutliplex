package com.example.bookingservice.dto;


import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Movie;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowResponseDto {
    private Long id;
    private Movie movie;
    // this will fetch all shows of this hall. set that to null
    private Hall hall;
    private Date fromDate;
    private Date toDate;
    private Integer slotNumber;
}
