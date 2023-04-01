package com.example.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowRequestDto {
    private Long movieId;
    private Long hallId;
    private Date fromDate;
    private Date toDate;
    private Integer slotNumber;
}
