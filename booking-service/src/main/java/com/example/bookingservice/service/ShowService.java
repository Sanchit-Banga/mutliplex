package com.example.bookingservice.service;

import com.example.bookingservice.dto.ShowRequestDto;
import com.example.bookingservice.dto.ShowResponseDto;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.repository.ShowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShowService {
    final ShowRepository showRepository;


    public String addShow(ShowRequestDto show) {

        return "Show added successfully";
    }


    public String updateShow(ShowRequestDto show, Long id) {
        try {
            return "Show updated successfully";
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Wrong input");
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException("Invalid ID data type");
        }
    }

    public List<ShowResponseDto> getAllShows() {
        List<ShowResponseDto> s = new ArrayList<>();
        return s;
    }

    public ShowResponseDto getShow() {
        return new ShowResponseDto();
    }

    public String deleteShow(Long id) {
        return "Show deleted successfully";
    }
}
