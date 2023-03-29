package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Shows {
    private Long showId;
    private long slotNo;

    private Date fromDate;

    private Date toDate;

    private Hall hall;

    private MoviesDto movies;

}