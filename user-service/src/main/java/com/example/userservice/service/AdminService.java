package com.example.userservice.service;

import com.example.userservice.dto.Hall;
import com.example.userservice.dto.MoviesDto;
import com.example.userservice.dto.Shows;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final WebClient.Builder webClientBuilder;

    public String addMovie(@RequestBody MoviesDto moviesDto) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().post()
                    .uri("http://localhost:8081/movies/addmovie")
                    .bodyValue(moviesDto)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/add-movie': {}", response);
        } catch (Exception e) {
            log.error("Error while adding movie: {}", e.getMessage());
            return "Movie already exists";
        }
        log.info("Response from '/api/v1/admin/add-movie': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }

    public String updateMovie(MoviesDto moviesDto, Long id) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().put()
                    .uri("http://localhost:8081/movies/updateMovie/" + id)
                    .bodyValue(moviesDto)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/update-movie': {}", response);
        } catch (Exception e) {
            log.error("Error while updating movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        log.info("Response from '/api/v1/admin/update-movie': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }

    public String deleteMovie(Long id) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().delete()
                    .uri("http://localhost:8081/movies/deleteMovie/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/delete-movie': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting movie: {}", e.getMessage());
            return "Movie does not exist";
        }
        log.info("Response from '/api/v1/admin/delete-movie': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }

    public List<MoviesDto> getAllMovies() {
        List<MoviesDto> response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/movies/getall")
                    .retrieve()
                    .bodyToFlux(MoviesDto.class)
                    .collectList()
                    .block();
            log.info("Response from '/api/v1/admin/get-all-movies': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all movies: {}", e.getMessage());
            return null;
        }
        log.info("Response from '/api/v1/admin/get-all-movies': {}", response);
        return response;
    }

    public String getMovieById(Long id) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/movies/getMovieById/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/get-movie-by-id': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by id: {}", e.getMessage());
            return null;
        }
        log.info("Response from '/api/v1/admin/get-movie-by-id': {}", response);
        return response.getOrDefault("message", "something went wrong");
    }

    public List<MoviesDto> getMovieByName(String name) {
        List<MoviesDto> response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/movies/getMovieByName/" + name)
                    .retrieve()
                    .bodyToFlux(MoviesDto.class)
                    .collectList()
                    .block();
            log.info("Response from '/api/v1/admin/get-movie-by-name': {}", response);
        } catch (Exception e) {
            log.error("Error while getting movie by name: {}", e.getMessage());
            return null;
        }
        log.info("Response from '/api/v1/admin/get-movie-by-name': {}", response);
        return response;
    }


    public List<Hall> getAllHalls() {
        List<Hall> response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/hall/getallhall")
                    .retrieve()
                    .bodyToFlux(Hall.class)
                    .collectList()
                    .block();
            log.info("Response from '/api/v1/admin/get-all-halls': {}", response);
        } catch (Exception e) {
            log.error("Error while getting all halls: {}", e.getMessage());
            return null;
        }
        log.info("Response from '/api/v1/admin/get-all-halls': {}", response);
        return response;
    }

    public Hall getHallById(Long id) {
        Hall response;
        try {
            response = webClientBuilder.build().get()
                    .uri("http://localhost:8081/hall/gethallid/" + id)
                    .retrieve()
                    .bodyToMono(Hall.class)
                    .block();
            log.info("Response from '/api/v1/admin/get-hall-by-id': {}", response);
        } catch (Exception e) {
            log.error("Error while getting hall by id: {}", e.getMessage());
            return null;
        }
        log.info("Response from '/api/v1/admin/get-hall-by-id': {}", response);
        return response;
    }

    public String addHall(Hall hall) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().post()
                    .uri("http://localhost:8081/hall/addhall")
                    .bodyValue(hall)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/add-hall': {}", response);
        } catch (Exception e) {
            log.error("Error while adding hall: {}", e.getMessage());
            return "Hall already exists";
        }
        log.info("Response from '/api/v1/admin/add-hall': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }


    public String deleteHall(Long id) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build()
                    .delete()
                    .uri("http://localhost:8081/hall/deletehallbyid/" + id)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/delete-hall': {}", response);
        } catch (Exception e) {
            log.error("Error while deleting hall: {}", e.getMessage());
            return "Hall does not exist";
        }
        log.info("Response from '/api/v1/admin/delete-hall': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }

    public String addShow(Shows shows) {
        Map<String, String> response;
        try {
            response = webClientBuilder.build().post()
                    .uri("http://localhost:8081/show/addshow")
                    .bodyValue(shows)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            log.info("Response from '/api/v1/admin/add-show': {}", response);
        } catch (Exception e) {
            log.error("Error while adding show: {}", e.getMessage());
            return "Show already exists";
        }
        log.info("Response from '/api/v1/admin/add-show': {}", response.getOrDefault("message", "something went wrong"));
        return response.getOrDefault("message", "something went wrong");
    }
}
