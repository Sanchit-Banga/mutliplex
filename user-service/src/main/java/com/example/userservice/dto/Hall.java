package com.example.userservice.dto;


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

public class Hall {
        private Long hallId;
        private String hallDescription;
        private int totalCapacity;
        private List<Shows> shows = new ArrayList<>();

    }
