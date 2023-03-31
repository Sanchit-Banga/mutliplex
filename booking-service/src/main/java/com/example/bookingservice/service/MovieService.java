package com.example.bookingservice.service;

import com.example.bookingservice.dto.MovieDtoRequest;
import com.example.bookingservice.dto.MovieDtoResponse;
import com.example.bookingservice.exceptions.AlreadyPresentException;
import com.example.bookingservice.exceptions.BadRequestException;
import com.example.bookingservice.exceptions.ConstraintViolationException;
import com.example.bookingservice.exceptions.NotFoundException;
import com.example.bookingservice.model.Movie;
import com.example.bookingservice.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MovieService {
    final MovieRepository movieRepository;

    public static final String INCORRECTINPUT="Input incorrect";
    public static final String INCORRECTDATATYPE="Incorrect datatype";

    public String addMovie(MovieDtoRequest moviedto) {
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
            throw new ConstraintViolationException(INCORRECTINPUT);
        }
    }


    public String updateMovie(MovieDtoRequest movieDtoRequest, Long id) {

        try{
            Movie movie = movieRepository.findByName(movieDtoRequest.getName());
            if(movie!=null){
                throw new AlreadyPresentException("Movie already present");
            }
            movie = movieRepository.findById(id).orElseThrow(()->new NotFoundException("Movie not found"));
            if(movie.getName().equals(movieDtoRequest.getName())){
                throw new AlreadyPresentException("Movie already exists");
            }
            movie.setName(movieDtoRequest.getName());
            movieRepository.save(movie);
        }
        catch (ConstraintViolationException e){
            throw new ConstraintViolationException(INCORRECTINPUT);
        }
        catch (MethodArgumentTypeMismatchException e){
            throw new BadRequestException(INCORRECTDATATYPE);
        }
        return "Movie updated successfully";
    }

    public MovieDtoResponse getMovie(String name) {
        Movie movie;
        try {
            movie = movieRepository.findByName(name);
            if (movie == null) {
                throw new NotFoundException("Movie not found exception");
            }
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException(INCORRECTINPUT);
        } catch (MethodArgumentTypeMismatchException e) {
            throw new BadRequestException(INCORRECTDATATYPE);
        }
        MovieDtoResponse dto = new MovieDtoResponse();
        dto.setName(movie.getName());
        dto.setId(movie.getId());
        return dto;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public String deleteMovie(Long id) {
        try{
            Movie movie = movieRepository.findById(id).orElseThrow(()->new NotFoundException("Movie not found"));
            movieRepository.deleteById(movie.getId());
        }
        catch (ConstraintViolationException e){
            throw new ConstraintViolationException(INCORRECTINPUT);
        }
        catch (MethodArgumentTypeMismatchException e){
            throw new BadRequestException(INCORRECTDATATYPE);
        }
        return "Movie deleted successfully";
    }
}
