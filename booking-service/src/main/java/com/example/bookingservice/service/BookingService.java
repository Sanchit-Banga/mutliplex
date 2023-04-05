package com.example.bookingservice.service;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.exceptions.NotFoundException;
import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Seat;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.ShowRepository;
import com.example.bookingservice.utils.MapToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final BookingDetailService bookingDetailService;

    public BookingResponseDto getBookingById(Long id) {
        try {
            Booking booking = bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
            List<Seat> bookedSeats = bookingDetailService.getBookingDetailById(booking.getId()).getSeat();
            List<SeatResponseDto> seats = bookedSeats.stream().map(MapToDto::toSeatResponseDto).toList();
            double totalPrice = 0;
            for (Seat seat : bookedSeats) {
                totalPrice += seat.getPrice();
            }
            Show show = booking.getShow();
            Hall hall = show.getHall();
            hall.setShow(null);
            hall.setSeats(null);
            ShowResponseDto showResponseDto = MapToDto.convertShowToDto(show, hall);
            return MapToDto.convertBookingToDto(booking, seats, totalPrice, showResponseDto);
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Input data type incorrect");
        }
    }

    public List<BookingDto> getAllBookings() {
        try {
            List<Booking> bookings = bookingRepository.findAll();
            return bookings.stream().map(MapToDto::convertBookingToDto).toList();
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
            BookingDetailsDto bookingDetailsDto = booking.getBookingDetails();
            Booking book = Booking.builder()
                    .show(show)
                    .bookedDate(new Date())
                    .showDate(booking.getShowDate())
                    .build();
            Long bookingDetailId = bookingDetailService.addBookingDetail(bookingDetailsDto);
            Booking booking1 = bookingRepository.saveAndFlush(book);
            bookingDetailService.setBookingInBookingDetail(booking1, bookingDetailId);
            log.info("Booking object: {}", booking1);
            return booking1.getId().toString();
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


}
