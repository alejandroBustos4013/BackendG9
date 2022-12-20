/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.MovieDto;
import com.movies.dto.ResponseDto;
import com.movies.entities.Gender;
import com.movies.entities.Movie;
import com.movies.entities.User;
import com.movies.repository.GenderRepository;
import com.movies.repository.MovieRepository;
import com.movies.repository.UserRepository;
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

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


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

    public ResponseDto create(MovieDto request, String authorization) {
        ResponseDto response = new ResponseDto();
        response.status= false;

        List<Movie> movies = repository.getByTitle(request.title);
        if(movies.size()>0){
            response.message=MovieRegistered;
        }else{
            Movie movie = new Movie();
            Optional<Gender> gender = genderRepository.findById(request.genderId);
            Optional<User> user = userService.getByCredential(authorization);

            if (gender.isPresent() && user.isPresent()){
                movie.setTitle(request.title);
                movie.setDescription(request.description);
                movie.setGenders(gender.get());
                movie.setDirector(request.director);
                movie.setLink(request.link);
                movie.setReleaseDate(request.releaseDate);
                movie.setUsers(user.get());
                movie.setImageLink(request.imageLink);
                repository.save(movie);
                response.status=true;
                response.message=MovieSuccess;
                response.id = movie.getId();


            }
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
