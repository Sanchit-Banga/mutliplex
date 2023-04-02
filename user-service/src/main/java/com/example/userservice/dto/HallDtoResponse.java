package com.example.userservice.dto;


import com.example.userservice.model.Show;
import com.example.userservice.utils.HallType;
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
