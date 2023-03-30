package com.example.bookingservice.service;


import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.dto.HallDtoResponse;
import com.example.bookingservice.repository.HallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HallService {
    final HallRepository hallRepository;


    public String addHall(HallDtoRequest hallDto) {
//        try{
//            if(hallRepository.findById(hallDto.getId()) != null){
//
//            }
//        }
        return "Hall added successfully";
    }

    public HallDtoResponse getHall(Long id) {
        return new HallDtoResponse();
    }


    public String updateHall(HallDtoRequest hallDto, Long id) {
        return "Hall updated successfully";
    }

    public String deleteHall(Long id) {
        return "Hall deleted successfully";
    }
}
