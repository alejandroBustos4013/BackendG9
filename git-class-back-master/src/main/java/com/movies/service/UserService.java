/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Gender;
import com.movies.entities.User;
import com.movies.repository.UserRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public Iterable<User> get() {
        Iterable<User> response = repository.getAll();
        return response;
    }

    public ResponseDto create(User request) {

        User newUser = repository.save(request);
        ResponseDto responseDto = new ResponseDto();
        responseDto.status = true;
        responseDto.message = "El usuario se creó de manera correcta";
        responseDto.id = newUser.getId();
        return responseDto;

    }

    public User update(User user) {
        User userToUpdate = new User();

        Optional<User> currentClient = repository.findById(user.getId());
        if (!currentClient.isEmpty()) {            
            userToUpdate = user;
            userToUpdate =repository.save(userToUpdate);
        }
        return userToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
