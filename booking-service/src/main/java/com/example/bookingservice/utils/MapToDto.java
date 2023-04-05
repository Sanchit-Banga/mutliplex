package com.example.bookingservice.utils;

import com.example.bookingservice.dto.*;
import com.example.bookingservice.model.*;

import java.util.List;

public class MapToDto {
    private MapToDto() {
        throw new IllegalStateException("Utility class");
    }

    public static BookingResponseDto convertBookingToDto(Booking booking, List<SeatResponseDto> seats, double totalPrice, ShowResponseDto show) {
        return BookingResponseDto.builder()
                .bookingId(booking.getId())
                .bookedDate(booking.getBookedDate())
                .showDate(booking.getShowDate())
                .seats(seats)
                .show(show)
                .totalPrice(totalPrice)
                .build();
    }

    public static ShowResponseDto convertShowToDto(Show show, Hall hall) {
        return ShowResponseDto.builder()
                .id(show.getId())
                .hall(hall)
                .movie(show.getMovie())
                .slotNumber(show.getSlotNumber())
                .build();
    }

    public static BookingDto convertBookingToDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .bookedDate(booking.getBookedDate())
                .showDate(booking.getShowDate())
                .show(booking.getShow())
                .build();
    }

    public static HallDtoResponse convertHallToDto(Hall hall, List<Show> shows) {
        return HallDtoResponse.builder()
                .id(hall.getId())
                .hallType(hall.getHallType())
                .totalCapacity(hall.getTotalCapacity())
                .shows(shows)
                .build();
    }

    public static Seat seatBuilder(Integer seatNumber, SeatType seatType, Double price) {
        return Seat.builder()
                .seatNumber(seatNumber)
                .seatType(seatType)
                .price(price)
                .isBooked(false)
                .build();
    }

    public static SeatResponseDto toSeatResponseDto(Seat seat) {
        return SeatResponseDto.builder()
                .seatNumber(seat.getSeatNumber())
                .seatType(seat.getSeatType())
                .price(seat.getPrice())
                .build();
    }

    public static Show convertShowDtoToModel(ShowRequestDto showRequestDto, Movie movie, Hall hall) {
        return Show.builder()
                .movie(movie)
                .fromDate(showRequestDto.getFromDate())
                .toDate(showRequestDto.getToDate())
                .hall(hall)
                .slotNumber(showRequestDto.getSlotNumber())
                .build();
    }

    public static ShowResponseDto convertToShowDto(Show show) {
        return ShowResponseDto.builder()
                .id(show.getId())
                .toDate(show.getToDate())
                .fromDate(show.getFromDate())
                .slotNumber(show.getSlotNumber())
                .movie(show.getMovie())
                .hall(show.getHall())
                .build();
    }
}
