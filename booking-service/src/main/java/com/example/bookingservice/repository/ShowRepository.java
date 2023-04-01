package com.example.bookingservice.repository;

import com.example.bookingservice.model.Hall;
import com.example.bookingservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Query(value = "select u from Show u where u.hall=?1 and u.slotNumber=?2 and u.fromDate>=?3 and u.toDate<=?4")
    Show hallSlotValidate(Hall hall, Integer slotNo, Date fromDate, Date toDate);

}
