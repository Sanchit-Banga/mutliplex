package com.example.userservice.service;

import com.example.userservice.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final WebClient.Builder webClientBuilder;

    public List<MovieDtoResponse> getAllMovies() {
        List<MovieDtoResponse> response;
        try {
            response = webClientBuilder.build().get().uri("lb://booking-service/movie/get")
                    .retrieve()
                    .bodyToFlux(MovieDtoResponse.class)
                    .collectList()
                    .block();
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
            response = webClientBuilder.build()
                    .get()
                    .uri("lb://booking-service/movie/get/" + id)
                    .retrieve()
                    .bodyToMono(MovieDtoResponse.class)
                    .block();
            log.info("Response from '/api/v1/admin/movie/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by id: {}", e.getMessage());
            return new MovieDtoResponse();
        }
        return response;
    }

    public MovieDtoResponse getMovieByName(String name) {
        MovieDtoResponse response;
        try {
            response = webClientBuilder.build()
                    .get()
                    .uri("lb://booking-service/movie/get/" + name)
                    .retrieve()
                    .bodyToMono(MovieDtoResponse.class)
                    .block();
            log.info("Response from '/api/v1/admin/movie/getByName/{name}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by name: {}", e.getMessage());
            return new MovieDtoResponse();
        }
        return response;
    }

    public String addMovie(MovieDtoResponse moviesDto) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .post()
                    .uri("lb://booking-service/movie/add")
                    .bodyValue(moviesDto)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            System.out.println(response);
            log.info("Response from '/api/v1/admin/movie/add': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while adding movie: {}", e.getMessage());
            return "Movie already exists";
        }
        return (String) response.get("message");
    }

    public String updateMovie(MovieDtoRequest moviesDtoRequest, Long id) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .put()
                    .uri("lb://booking-service/movie/update/" + id)
                    .bodyValue(moviesDtoRequest).retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/movie/update/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while updating movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        return (String) response.get("message");
    }

    public String deleteMovie(Long id) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .delete()
                    .uri("lb://booking-service/movie/delete/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/movie/delete/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        return response.get("message").toString();
    }

    public List<HallDtoResponse> getAllHalls() {
        List<HallDtoResponse> response;
        try {
            response = webClientBuilder.build()
                    .get()
                    .uri("lb://booking-service/hall/get")
                    .retrieve()
                    .bodyToFlux(HallDtoResponse.class)
                    .collectList()
                    .block();
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
            response = webClientBuilder.build().get()
                    .uri("lb://booking-service/hall/get/" + id)
                    .retrieve()
                    .bodyToMono(HallDtoResponse.class)
                    .block();
            log.info("Response from '/api/v1/admin/hall/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting hall by id: {}", e.getMessage());
            return new HallDtoResponse();
        }
        return response;
    }

    public String addHall(HallDtoRequest hall) {
        Map response;
        try {
            response = webClientBuilder
                    .build()
                    .post()
                    .uri("lb://booking-service/hall/add")
                    .bodyValue(hall).retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/hall/add': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while adding hall: {}", e.getMessage());
            return "Hall already exists";
        }
        return response.get("message").toString();
    }

    public String deleteHall(Long id) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .delete()
                    .uri("lb://booking-service/hall/delete/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/hall/delete/{id}': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while deleting hall: {}", e.getMessage());
            return "Hall does not exist";
        }
        return response.get("message").toString();
    }

    public String addShow(ShowRequestDto show) {
        Map response;
        try {
            response = webClientBuilder.build()
                    .post()
                    .uri("lb://booking-service/show/add")
                    .bodyValue(show).retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/show/add': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while adding show: {}", e.getMessage());
            return "Show already exists";
        }
        return response.get("message").toString();
    }

    public List<ShowResponseDto> getAllShows() {
        List<ShowResponseDto> response;
        try {
            response = webClientBuilder
                    .build()
                    .get()
                    .uri("lb://booking-service/show/get")
                    .retrieve().bodyToFlux(ShowResponseDto.class)
                    .collectList()
                    .block();
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
            response = webClientBuilder.build()
                    .get()
                    .uri("lb://booking-service/show/get/" + id)
                    .retrieve()
                    .bodyToMono(ShowResponseDto.class)
                    .block();
            log.info("Response from '/api/v1/admin/show/get/{id}': {}", response);
        } catch (Exception e) {
            log.error("Error while getting show by id: {}", e.getMessage());
            return new ShowResponseDto();
        }
        return response;
    }

    public String deleteShow(Long id) {
        Map response;
        try {
            response = webClientBuilder
                    .build()
                    .delete()
                    .uri("lb://booking-service/show/delete/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/show/delete/{id}': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while deleting show: {}", e.getMessage());
            return "Show does not exist";
        }
        return response.get("message").toString();
    }

    public String updateShow(ShowRequestDto show, Long id) {
        Map response;
        try {
            response = webClientBuilder
                    .build()
                    .put()
                    .uri("lb://booking-service/show/update/" + id)
                    .bodyValue(show)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/show/update/{id}': {}", response.get("message"));
        } catch (Exception e) {
            log.error("Error while updating show: {}", e.getMessage());
            return "Show does not exist";
        }
        return response.get("message").toString();
    }
}
