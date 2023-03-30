package com.example.bookingservice.dto;

import com.example.bookingservice.model.Show;
import com.example.bookingservice.utils.HallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HallDtoRequest {
    private HallType hallType;
    private Long totalCapacity;
    private List<Show> shows = new ArrayList<>();
}
