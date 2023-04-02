package com.example.bookingservice.service;

import com.example.bookingservice.dto.ShowRequestDto;
import com.example.bookingservice.dto.ShowResponseDto;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.exceptions.NotFoundException;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Movie;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.repository.HallRepository;
import com.example.bookingservice.repository.MovieRepository;
import com.example.bookingservice.repository.ShowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ShowService {
    public static final String NOHALL = "No Hall Found";
    public static final String NOSHOW = "No Show Found";
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final HallRepository hallRepository;

    public boolean dateValidate(Date fromDate, Date toDate) {
        // returns true if fromDate is after toDate
        return (fromDate.after(toDate));
    }

    public boolean hallSlotValidate(Long hallId, Integer slotNo, Date fromDate, Date toDate) {
        //returns true if there is already hall booked on that slot no
        Hall hall = hallRepository.findById(hallId).orElseThrow(() -> new BadRequestException(NOHALL));
        Show show = showRepository.hallSlotValidate(hall, slotNo, fromDate, toDate);
        return show != null;
    }


    public String addShow(ShowRequestDto showRequestDto) {

        try {
            if (dateValidate(showRequestDto.getFromDate(), showRequestDto.getToDate())) {
                return "From date cannot be after To Date";
            }
            if (hallSlotValidate(showRequestDto.getHallId(), showRequestDto.getSlotNumber(), showRequestDto.getFromDate(), showRequestDto.getToDate())) {
                return "Show already present at this slot no";
            }

            Hall hall = hallRepository.findById(showRequestDto.getHallId()).orElseThrow(() -> new BadRequestException(NOHALL));
            Movie movie = movieRepository.findById(showRequestDto.getMovieId()).orElseThrow(() -> new BadRequestException("No movie found"));

            Show show = Show.builder()
                    .movie(movie)
                    .fromDate(showRequestDto.getFromDate())
                    .toDate(showRequestDto.getToDate())
                    .hall(hall)
                    .slotNumber(showRequestDto.getSlotNumber())
                    .build();
            showRepository.save(show);
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Wrong input");
        }

        return "Show added successfully";
    }

    public String updateShow(ShowRequestDto showRequestDto, Long showId) {
        try {

            Show show = showRepository.findById(showId).orElseThrow(() -> new NotFoundException(NOSHOW));

            if (showRequestDto.getSlotNumber() != null) {
                show.setSlotNumber(showRequestDto.getSlotNumber());
            }

            if (showRequestDto.getHallId() != null) {
                show.setHall(hallRepository.findById(showRequestDto.getHallId()).get());
            }

            if (showRequestDto.getMovieId() != null) {
                show.setMovie(movieRepository.findById(showRequestDto.getMovieId()).get());
            }

            if (showRequestDto.getFromDate() != null) {
                show.setFromDate(showRequestDto.getFromDate());
            }

            if (showRequestDto.getToDate() != null) {
                show.setFromDate(showRequestDto.getToDate());
            }

            showRepository.save(show);

        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Wrong input");
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Invalid ID data type");
        }
        return "Show updated successfully";
    }

    private ShowResponseDto convertToDto(Show show) {
        return ShowResponseDto.builder().id(show.getId()).toDate(show.getToDate()).fromDate(show.getFromDate()).slotNumber(show.getSlotNumber()).movie(show.getMovie()).hall(show.getHall()).build();
    }


    public List<ShowResponseDto> getAllShows() {
        try {
            List<Show> allShows = showRepository.findAll();
            log.info("All Shows = {}", allShows);
            return allShows.stream().map(this::convertToDto).toList();
        } catch (Exception e) {
            throw new BadRequestException(NOSHOW);
        }
    }

    public ShowResponseDto getShow(Long id) {
        try {
            Show show = showRepository.findById(id).orElseThrow(() -> new BadRequestException(NOSHOW));
            log.info("Show =  {}", show);
            return convertToDto(show);
        } catch (Exception e) {
            throw new BadRequestException(NOSHOW);
        }
    }

    public String deleteShow(Long id) {
        Show show = showRepository.findById(id).orElseThrow(() -> new NotFoundException(NOSHOW));
        showRepository.deleteById(show.getId());
        return "Show deleted successfully";
    }

}
