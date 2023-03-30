package com.example.bookingservice.model;

import com.example.bookingservice.utils.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Embeddable
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer seatNumber;
    private Double price;
    @Column(columnDefinition = "boolean default false")
    private Boolean isBooked;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
