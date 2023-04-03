package com.example.bookingservice.repository;

import com.example.bookingservice.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s FROM Seat s WHERE s.seatNumber = :seatNumber")
    public Seat getSeatBySeatNumber(@Param("seatNumber") Integer seatNumber);
}
