package com.example.bookingservice.model;


import com.example.bookingservice.utils.HallType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@Entity
@Getter
@Setter
@Builder
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    @JsonManagedReference
    private List<Show> show;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private List<Seat> seats;
}
