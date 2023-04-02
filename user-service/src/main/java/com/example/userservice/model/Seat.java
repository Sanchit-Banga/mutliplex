package com.example.userservice.model;

import com.example.userservice.utils.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
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
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
}
