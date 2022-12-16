/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.dto.ScoreDto;
import com.movies.entities.Movie;
import com.movies.entities.Score;
import com.movies.entities.User;
import com.movies.repository.MovieRepository;
import com.movies.repository.ScoreRepository;
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
public class ScoreService {

    private final String scoreAccepted = "calificación guardada exitosamente";
    private final String scoreDeclined = "La calificación no se encuentra dentro del rango establecido 1-5";
    private final String  scoreDeclinedRepeat = "El usuario ya calificó esta pelicula";
    private final String movieDeclinedNoExist = "La pelicula no existe";

    @Autowired
    ScoreRepository repository;


    @Autowired
    UserService userService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;



    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(ScoreDto request) {

        ResponseDto response = new ResponseDto();
        response.status = false;
        //List<Score> scoresUserAndMovieById = repository.getByMovieAndUserById(request.movieId, request.userId);


        if (request.score < 1 || request.score > 5) { //para evaluar la puntuacion
            response.message = scoreDeclined;
        } /*else if (scoresUserAndMovieById.size() > 0) {
            response.status = false;
            response.message = scoreDeclinedRepeat;
        }*/ else {
            Score score = new Score();
            Optional<Movie> movie = movieRepository.findById(request.movieId);
            Optional<User> user = userRepository.findById(request.userId);




            if (movie.isPresent() && user.isPresent()) {
                score.setScore(request.score);
                score.setMovies(movie.get());
                score.setUsers(user.get());
                repository.save(score);
                response.status = true;
                response.message = scoreAccepted;
                response.id = score.getId();
            }
        }
        return response;
    }

    public Score update(Score score) {
        Score scoreToUpdate = new Score();

        Optional<Score> currentScore = repository.findById(score.getId());
        if (!currentScore.isEmpty()) {
           scoreToUpdate = score;
            scoreToUpdate =repository.save(scoreToUpdate);
        }
        return scoreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
