package com.example.bookingservice.repository;

import com.example.bookingservice.dto.HallDtoRequest;
import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Query(value = "select u from Show u where u.hall=?1 and u.slotNumber=?2")
    Show hallSlotValidate(HallDtoRequest hall, Integer slotNo);

}
