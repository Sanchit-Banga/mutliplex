package com.example.bookingservice.service;


import com.example.bookingservice.dto.HallDto;
import com.example.bookingservice.repository.HallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HallService {
    final HallRepository hallRepository;


    public String addHall(HallDto hallDto) {
//        try{
//            if(hallRepository.findById(hallDto.getId()) != null){
//
//            }
//        }
        return "Hall added successfully";
    }
}
