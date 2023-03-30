package com.example.bookingservice.repository;

import com.example.bookingservice.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}
