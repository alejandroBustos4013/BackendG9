/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.controller;

import com.movies.dto.AuthDto;
import com.movies.dto.AuthResponseDto;
import com.movies.dto.ResponseDto;
import com.movies.entities.User;
import com.movies.service.AuthService;
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
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    AuthService service;


    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthResponseDto check(@RequestBody AuthDto request) {
        return service.check(request);
    }


}
