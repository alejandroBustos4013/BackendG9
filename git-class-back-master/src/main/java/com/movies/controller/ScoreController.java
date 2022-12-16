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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> create(@RequestBody ScoreDto request) {
        ResponseDto responseDto = service.create(request);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto,HttpStatus.CONFLICT);

        if(responseDto.status.booleanValue()==true){
            response = new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }
        return response;
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Score update(@RequestBody Score request) {
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}
