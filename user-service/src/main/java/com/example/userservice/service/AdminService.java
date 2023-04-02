package com.example.userservice.service;

import com.example.userservice.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final WebClient.Builder webClientBuilder;

    public List<MovieDtoResponse> getAllMovies() {
        List<MovieDtoResponse> response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/movie/get").retrieve().bodyToFlux(MovieDtoResponse.class).collectList().block();
            log.info("Response from '/api/v1/admin/movie/get': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all movies: {}", e.getMessage());
            return Collections.emptyList();
        }
        return response;
    }

    public MovieDtoResponse getMovieById(Long id) {
        MovieDtoResponse response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/movie/get/" + id).retrieve().bodyToMono(MovieDtoResponse.class).block();
            log.info("Response from '/api/v1/admin/movie/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by id: {}", e.getMessage());
            return new MovieDtoResponse(null, "Not Found");
        }
        return response;
    }

    public MovieDtoResponse getMovieByName(String name) {
        MovieDtoResponse response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/movie/get/" + name).retrieve().bodyToMono(MovieDtoResponse.class).block();
            log.info("Response from '/api/v1/admin/movie/getByName/{name}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by name: {}", e.getMessage());
            return new MovieDtoResponse(null, "Not Found");
        }
        return response;
    }

    public String addMovie(MovieDtoResponse moviesDto) {
        String response;
        try {
            response = webClientBuilder.build().post().uri("http://localhost:8089/movie/add").bodyValue(moviesDto).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/movie/add': {}", response);
        } catch (Exception e) {
            log.error("Error while adding movie: {}", e.getMessage());
            return "Movie already exists";
        }
        return response;
    }

    public String updateMovie(MovieDtoRequest moviesDtoRequest, Long id) {
        String response;
        try {
            response = webClientBuilder.build().put().uri("http://localhost:8089/movie/update/" + id).bodyValue(moviesDtoRequest).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/movie/update/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while updating movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        return response;
    }

    public String deleteMovie(Long id) {
        String response;
        try {
            response = webClientBuilder.build().delete().uri("http://localhost:8089/movie/delete/" + id).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/movie/delete/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        return response;
    }

    public List<HallDtoResponse> getAllHalls() {
        List<HallDtoResponse> response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/hall/get").retrieve().bodyToFlux(HallDtoResponse.class).collectList().block();
            log.info("Response from '/api/v1/admin/hall/get': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all halls: {}", e.getMessage());
            return Collections.emptyList();
        }
        return response;
    }


    public HallDtoResponse getHallById(Long id) {
        HallDtoResponse response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/hall/get/" + id).retrieve().bodyToMono(HallDtoResponse.class).block();
            log.info("Response from '/api/v1/admin/hall/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting hall by id: {}", e.getMessage());
            return new HallDtoResponse();
        }
        return response;
    }

    public String addHall(HallDtoRequest hall) {
        String response;
        try {
            response = webClientBuilder.build().post().uri("http://localhost:8089/hall/add").bodyValue(hall).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/hall/add': {}", response);
        } catch (Exception e) {
            log.error("Error while adding hall: {}", e.getMessage());
            return "Hall already exists";
        }
        return response;
    }

    public String deleteHall(Long id) {
        String response;
        try {
            response = webClientBuilder.build().delete().uri("http://localhost:8089/hall/delete/" + id).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/hall/delete/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting hall: {}", e.getMessage());
            return "Hall does not exist";
        }
        return response;
    }

    public String addShow(ShowRequestDto show) {
        String response;
        try {
            response = webClientBuilder.build().post().uri("http://localhost:8089/show/add").bodyValue(show).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/show/add': {}", response);
        } catch (Exception e) {
            log.error("Error while adding show: {}", e.getMessage());
            return "Show already exists";
        }
        return response;
    }

    public List<ShowResponseDto> getAllShows() {
        List<ShowResponseDto> response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/show/get").retrieve().bodyToFlux(ShowResponseDto.class).collectList().block();
            log.info("Response from '/api/v1/admin/show/get': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all shows: {}", e.getMessage());
            return Collections.emptyList();
        }
        return response;
    }

    public ShowResponseDto getShowById(Long id) {
        ShowResponseDto response;
        try {
            response = webClientBuilder.build().get().uri("http://localhost:8089/show/get/" + id).retrieve().bodyToMono(ShowResponseDto.class).block();
            log.info("Response from '/api/v1/admin/show/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting show by id: {}", e.getMessage());
            return new ShowResponseDto();
        }
        return response;
    }

    public String deleteShow(Long id) {
        String response;
        try {
            response = webClientBuilder.build().delete().uri("http://localhost:8089/show/delete/" + id).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/show/delete/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting show: {}", e.getMessage());
            return "Show does not exist";
        }
        return response;
    }

    public String updateShow(ShowRequestDto show, Long id) {
        String response;
        try {
            response = webClientBuilder.build().put().uri("http://localhost:8089/show/update/" + id).bodyValue(show).retrieve().bodyToMono(String.class).block();
            log.info("Response from '/api/v1/admin/show/update/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while updating show: {}", e.getMessage());
            return "Show does not exist";
        }
        return response;
    }
}
