package com.example.bookingservice.service;

import com.example.bookingservice.dto.BookingDto;
import com.example.bookingservice.dto.BookingRequestDto;
import com.example.bookingservice.exceptions.AlreadyPresentException;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.exceptions.NotFoundException;
import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;

    public BookingDto getBookingById(Long id) {
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
            return convertBookingToDto(booking);
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Input data type incorrect");
        }
    }

    public List<BookingDto> getAllBookings() {
        try {
            List<Booking> bookings = bookingRepository.findAll();
            return bookings.stream().map(this::convertBookingToDto).toList();
        } catch (Exception e) {
            log.error("Error at getting all bookings", e);
            throw new BadRequestException("Something went wrong");
        }
    }

    public String add(BookingRequestDto booking) {
        try {
            Show show = showRepository.findById(booking.getShowId()).orElseThrow(() -> new NotFoundException("Show not found"));
            Date date = booking.getShowDate();
            if (date.after(show.getToDate()) || date.before(show.getFromDate())) {
                throw new BadRequestException("Date invalid");
            }
            Booking book = Booking.builder()
                    .show(show)
                    .bookedDate(booking.getBookedDate())
                    .showDate(booking.getShowDate())
                    .build();
            bookingRepository.save(book);
            return "Booking added successfully";
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Invalid input");
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Invalid input datatype");
        }
    }

    public String deleteBooking(Long id) {
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
            bookingRepository.delete(booking);
            return "Booking cancelled successfully";
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Invalid input data type");
        }
    }

    private BookingDto convertBookingToDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookedDate(booking.getBookedDate())
                .showDate(booking.getShowDate())
                .show(booking.getShow())
                .build();
    }
}
