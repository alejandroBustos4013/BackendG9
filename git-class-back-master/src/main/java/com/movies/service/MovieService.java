/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Movie;
import com.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
@Service
public class MovieService {

    private final String MovieRegistered="Pelicua ya se encuentra registrada";
    private final String MovieSuccess="Pelicula ingresada exitosamente";

    @Autowired
    MovieRepository repository;

    public Iterable<Movie> get() {
        Iterable<Movie> response = repository.getAll();
        return response;
    }

    public Iterable<Movie> findMoviesByGender(String name) {
        Iterable<Movie> response = repository.findMoviesByGender(name);
        return response;
    }

    public Optional<Movie> getById(String id) {
        Optional<Movie> response = repository.findById(id);
        return response;
    }

    public ResponseDto create(Movie request) {
        ResponseDto response = new ResponseDto();
        List<Movie> movies = repository.getByTitle(request.getTitle());
        if(movies.size()>0){
            response.status=false;
            response.message=MovieRegistered;
        }else{
            repository.save(request);
            response.status=true;
            response.message=MovieSuccess;
            response.id= request.getId();
        }
        return response;
    }

    public Movie update(Movie movie) {
        Movie movieToUpdate = new Movie();

        Optional<Movie> currentMovie = repository.findById(movie.getId());
        if (!currentMovie.isEmpty()) {
            movieToUpdate = movie;
            movieToUpdate=repository.save(movieToUpdate);
        }
        return movieToUpdate;
    }
    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
