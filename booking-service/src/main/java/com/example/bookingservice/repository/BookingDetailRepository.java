package com.example.bookingservice.repository;

import com.example.bookingservice.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,Long> {
}
