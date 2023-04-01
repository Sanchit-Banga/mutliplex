package com.example.bookingservice.service;

import com.example.bookingservice.model.BookingDetail;
import com.example.bookingservice.repository.BookingDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;

    public List<BookingDetail> getBookingDetailAll() {
        return bookingDetailRepository.findAll();
    }

    public String addBookingDetail(BookingDetail bookingDetail) {
        bookingDetailRepository.save(bookingDetail);
        return "Booking detail added successfully";
    }

    public BookingDetail getById(Long id) {
        Optional<BookingDetail> optional = bookingDetailRepository.findById(id);
        return optional.get();
    }

    public String deleteBookingDetail(Long id) {
        return "deleted successfully";
    }
}
