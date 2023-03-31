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
    private Integer totalCapacity;
    @ElementCollection
    private List<Show> shows = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private List<Seat> seats;
}
