package com.example.bookingservice.dto;

import com.example.bookingservice.model.Seat;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.utils.HallType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallDtoResponse {
    private Long id;
    private HallType hallType;
    private Integer totalCapacity;
    private List<Show> shows = new ArrayList<>();
}
