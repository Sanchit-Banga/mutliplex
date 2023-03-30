package com.example.bookingservice.model;


import com.example.bookingservice.utils.HallType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@ToString
@Table(name = "hall")
@AllArgsConstructor
@NoArgsConstructor
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HallType hallType;
    private Long totalCapacity;
    @ElementCollection
    private List<Show> shows = new ArrayList<>();
    @ElementCollection
    private List<Seat> seats = new ArrayList<>();
}
