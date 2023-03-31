package com.example.bookingservice.service;

import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.dto.ShowRequestDto;
import com.example.bookingservice.dto.ShowResponseDto;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Show;
import com.example.bookingservice.repository.ShowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ShowService {
    final ShowRepository showRepository;
    public boolean dateValidate(Date fromDate, Date toDate){
        //returns true if fromDate is after toDate
        return (fromDate.after(toDate));
    }

    public boolean hallSlotValidate(HallDtoRequest hall, Integer slotNo){
        //returns true if there is already hall booked on that slot no
        Show show = showRepository.hallSlotValidate(hall,slotNo);
        return show!=null;
    }


    public String addShow(ShowRequestDto showRequestDto) {

        try{
            if(dateValidate(showRequestDto.getFromDate(),showRequestDto.getToDate())){
                return "From date cannot be after To Date";
            }
            if(hallSlotValidate(showRequestDto.getHall(),showRequestDto.getSlotNumber())){
                return "Show already present at this ";
            }
        }
        catch(Exception e){

        }

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
