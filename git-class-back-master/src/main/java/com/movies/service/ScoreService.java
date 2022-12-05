/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Score;
import com.movies.entities.User;
import com.movies.repository.ScoreRepository;
import com.movies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author Andres
 */
@Service
public class ScoreService {


    @Autowired
    ScoreRepository repository;

    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
        ResponseDto response = new ResponseDto();
        if(request.getScore().intValue()<1 || request.getScore().intValue()>5){
            response.status=false;
            response.message="Calificación fuera de los criterios (1 - 5)";
        }else{
            repository.save(request);
            response.status=true;
            response.message="Calificación guardada exitosamente";
            response.id= request.getId();
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
