package com.example.bookingservice.service;

import com.example.bookingservice.dto.MovieDto;
import com.example.bookingservice.exceptions.AlreadyPresentException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.model.Movie;
import com.example.bookingservice.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MovieService {
    final MovieRepository movieRepository;

    public String addMovie(MovieDto moviedto) {
        try{
            if(movieRepository.findByName(moviedto.getName())!=null){
                throw new AlreadyPresentException("Movie already exists");
            }
            Movie movie = Movie.builder()
                    .name(moviedto.getName())
                    .build();
            movieRepository.save(movie);
            return "Movie added successfully";
        } catch (ConstraintViolationException e){
            throw new ConstraintViolationException("Input incorrect");
        }
    }


    public String updateMovie(MovieDto movieDto, Long id) {
        return "Movie updated successfully";
    }

    public MovieDto getMovie(String name) {
        MovieDto dto = new MovieDto();
        return dto;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public String deleteMovie(Long id) {
        return "Movie deleted successfully";
    }
}
