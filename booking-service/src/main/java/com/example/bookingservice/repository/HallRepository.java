package com.example.bookingservice.repository;

import com.example.bookingservice.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long> {

}
