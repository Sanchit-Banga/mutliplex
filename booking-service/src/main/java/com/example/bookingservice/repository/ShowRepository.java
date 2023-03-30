package com.example.bookingservice.repository;

import com.example.bookingservice.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

}
