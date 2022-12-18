/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.controller;

import com.movies.dto.ResponseDto;
import com.movies.dto.ScoreDto;
import com.movies.entities.Score;
import com.movies.entities.User;
import com.movies.service.ScoreService;
import com.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 *
 * @author Andres
 */
@RestController
@RequestMapping("/api/score")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    ScoreService service;

    @GetMapping("")
    public Iterable<Score> get() {
        return service.get();
    }

    @GetMapping("/check/{movieId}")
    public Score check(@PathVariable("movieId") String movieId,@RequestHeader(value="authorization") String authorization) {
        return service.check(movieId,authorization);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto create(@RequestBody ScoreDto request,@RequestHeader(value="authorization") String authorization) {
        return service.create(request,authorization);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseDto update(@PathVariable("id") String id,@RequestBody Score request) {
        return service.update(request,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
