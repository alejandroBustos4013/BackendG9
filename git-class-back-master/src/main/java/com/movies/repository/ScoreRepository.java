/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.repository;

import com.movies.entities.Movie;
import com.movies.entities.Score;
import com.movies.entities.User;
import com.movies.interfaces.IScoreRepository;
import com.movies.interfaces.IUserRepository;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
@Repository
public class ScoreRepository {
    
    @Autowired
    IScoreRepository repository;


    
    public Iterable<Score> getAll(){

        return repository.findAll();
    }


    public List<Score> getByMovieAndUser(Movie movie, User users){
        return repository.getScoreByMoviesAndUser(movie, users);
    }

    public List<Score> getByMovieAndUserById(String movieId, String userId){
        return repository.getScoreByMoviesAndUserById(movieId, userId);
    }

    public List<Score> findByMovieAndUserById(String movieId, String userId){
        List<Score> response = repository.getScoreByMoviesAndUserById(movieId,userId);
        return response;
    }


    
    public Optional<Score> findById(String id){
        Optional<Score> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById (String id){

        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Score save(Score score){

        return repository.save(score);
    }
    
}
