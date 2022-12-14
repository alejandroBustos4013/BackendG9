/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.controller;

import com.movies.dto.MovieDto;
import com.movies.dto.ResponseDto;
import com.movies.dto.ScoreDto;
import com.movies.entities.Movie;
import com.movies.entities.Score;
import com.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
@RestController
@RequestMapping("/api/movie")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    MovieService service;

    @GetMapping("")
    public Iterable<Movie> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public Optional<Movie> getById(@PathVariable("id") String id) {
        return service.getById(id);
    }

    @GetMapping("/gender/{name}")
    public Iterable<Movie> findMoviesByGender(@PathVariable("name") String name) {
        return service.findMoviesByGender(name);
    }



    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody MovieDto request, @RequestHeader(value="authorization") String authorization) {
        return service.create(request,authorization);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie update(@RequestBody Movie request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
