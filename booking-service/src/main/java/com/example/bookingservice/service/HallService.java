package com.example.bookingservice.service;


import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.dto.HallDtoResponse;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Seat;
import com.example.bookingservice.repository.HallRepository;
import com.example.bookingservice.utils.SeatType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;
    private final SeatService seatService;

    public String addHall(HallDtoRequest hallDto) {
        try {
            if (hallDto.getHallType() == null || hallDto.getTotalCapacity() <= 0)
                throw new BadRequestException("Hall not added");
            Hall hall = Hall.builder()
                    .hallType(hallDto.getHallType())
                    .totalCapacity(hallDto.getTotalCapacity())
                    .shows(hallDto.getShows())
                    .build();
            addSeatToHall(hall);
            hallRepository.save(hall);
            log.info("Hall added successfully: {}", hall);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new BadRequestException("Hall not added");
        }
        return "Hall added successfully";
    }

    public HallDtoResponse getHall(Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No hall found"));
            log.info("Hall: {}", hall);
            return convertToDto(hall);
        } catch (Exception e) {
            throw new BadRequestException("No hall found");
        }
    }


    public String deleteHall(Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No hall found"));
            hallRepository.delete(hall);
            log.info("Hall deleted successfully: {}", hall);
        } catch (Exception e) {
            throw new BadRequestException("No hall found");
        }
        return "Hall deleted successfully";
    }

    public List<HallDtoResponse> getAllHalls() {
        try {
            List<Hall> allHalls = hallRepository.findAll();
            log.info("All halls: {}", allHalls);
            return allHalls.stream().map(this::convertToDto).toList();
        } catch (Exception e) {
            throw new BadRequestException("No halls found");
        }
    }

    public List<Seat> getAllSeatsOfHall(Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No hall found"));
            log.info("Hall: {}", hall);
            return hall.getSeats();
        } catch (Exception e) {
            throw new BadRequestException("No hall found");
        }
    }

    public String updateHall(HallDtoRequest hallDto, Long id) {
        try {
            Hall hall = hallRepository.findById(id).orElseThrow(() -> new BadRequestException("No such hall found"));
            int capacityDifference = hallDto.getTotalCapacity() - hall.getTotalCapacity();
            if (capacityDifference > 0) {
                hall.setTotalCapacity(hallDto.getTotalCapacity());
                addSeatToHall(hall);
            } else {
                List<Seat> seats = hall.getSeats();
                List<Seat> unbookedSeats = new ArrayList<>();
                for (Seat seat : seats) {
                    if (Boolean.FALSE.equals(seat.getIsBooked())) {
                        unbookedSeats.add(seat);
                    }
                }
                if (unbookedSeats.size() < Math.abs(capacityDifference)) {
                    throw new BadRequestException("Cannot reduce hall capacity");
                } else {
                    List<Seat> seatsToDelete = unbookedSeats.subList(0, Math.abs(capacityDifference));
                    for (Seat seat : seatsToDelete) {
                        seatService.deleteSeat(seat.getId());
                    }
                    hall.setTotalCapacity(hallDto.getTotalCapacity());
                }
            }
            hall.setHallType(hallDto.getHallType());
            hallRepository.save(hall);
        } catch (Exception e) {
            throw new BadRequestException("No hall found");
        }
        return "Hall updated successfully";
    }

    public Seat getSeatFromHallById(Long hallId, Long seatId) {
        try {
            Hall hall = hallRepository.findById(hallId).orElseThrow(() -> new BadRequestException("No such hall found"));
            Seat seat = hall.getSeats()
                    .stream()
                    .filter(s -> s.getId().equals(seatId))
                    .findFirst()
                    .orElseThrow(() ->
                            new BadRequestException("No such seat found"));
            log.info("Seat: {}", seat);
            return seat;
        } catch (Exception e) {
            throw new BadRequestException("No hall found");
        }
    }

    // logic for adding seats to hall
    private void addSeatToHall(Hall hall) {
        Seat s = null;
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= hall.getTotalCapacity(); i++) {
            int platinumSeats = hall.getTotalCapacity() * 20 / 100;
            int goldSeats = hall.getTotalCapacity() * 30 / 100;
            if (i <= platinumSeats) {
                s = seatBuilder(i, SeatType.PLATINUM, 200.0);
            } else if (i <= platinumSeats + goldSeats) {
                s = seatBuilder(i, SeatType.GOLD, 150.0);
            } else {
                s = seatBuilder(i, SeatType.SILVER, 100.0);
            }
            seats.add(s);
        }
        hall.setSeats(seats);
    }

    private HallDtoResponse convertToDto(Hall hall) {
        return HallDtoResponse.builder()
                .id(hall.getId())
                .hallType(hall.getHallType())
                .totalCapacity(hall.getTotalCapacity())
                .shows(hall.getShows())
                .build();
    }


    private Seat seatBuilder(Integer seatNumber, SeatType seatType, Double price) {
        return Seat.builder()
                .seatNumber(seatNumber)
                .seatType(seatType)
                .price(price)
                .isBooked(false)
                .build();
    }


}
