package com.example.bookingservice.service;

import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.model.BookingDetail;
import com.example.bookingservice.model.Seat;
import com.example.bookingservice.repository.BookingDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;

    public List<BookingDetail> getAllBookingDetails() {
        try {
            return bookingDetailRepository.findAll();
        } catch (Exception e) {
            throw new BadRequestException("Error while fetching all booking details");
        }
    }

    public String addBookingDetail(BookingDetail bookingDetail) {
        try {
            if (bookingDetail.getNumberOfSeats() <= 0)
                throw new BadRequestException("Number of seats cannot be less than or equal to 0");
            bookingDetail.getSeat().forEach(seat -> {
                if (seat.getIsBooked()) {
                    throw new BadRequestException("Seat " + seat.getId().toString() + " is already booked");
                }
            });
            bookingDetailRepository.save(bookingDetail);
            return "Booking detail added successfully";
        } catch (Exception e) {
            throw new BadRequestException("Error while adding booking detail");
        }
    }

    public BookingDetail getById(Long id) {
        return bookingDetailRepository.findById(id)
                .orElseThrow(()
                        -> new BadRequestException("Booking detail not found"));
    }

    public String deleteBookingDetail(Long id) {
        try {
            BookingDetail bookingDetail = bookingDetailRepository.findById(id)
                    .orElseThrow(()
                            -> new BadRequestException("No such booking found"));
            List<Seat> seats = bookingDetail.getSeat();
            seats.forEach(seat -> seat.setIsBooked(false));
            bookingDetailRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Error while deleting booking detail");
        }
        return "deleted successfully";
    }
}
