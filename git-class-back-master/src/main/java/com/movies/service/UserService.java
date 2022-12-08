/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Gender;
import com.movies.entities.Movie;
import com.movies.entities.User;
import com.movies.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@Service
public class UserService {

    private final String UserRegistered="Usuario ya se encuentra registrado";
    private final String UsernameRegistered ="Nombre de usuario ya se encuentra en uso";
    private final String UserSuccess="Usuario registrado exitosamente";

    @Autowired
    UserRepository repository;

    public Iterable<User> get() {
        Iterable<User> response = repository.getAll();
        return response;
    }

    public ResponseDto create(User request) {

        ResponseDto response = new ResponseDto();
        List<User> users = repository.getByName(request.getFull_name());
        List<User> usersname = repository.getByUsername(request.getUsername());
        List<User> usersemail = repository.getByEmail(request.getEmail());
        List<User> userscc = repository.getByCC(request.getNumber_identification());
        if(users.size()>0 || userscc.size()>0 || usersemail.size()>0 ){
            response.status=false;
            response.message= UserRegistered;
        }else if (usersname.size()>0) {
            response.status=false;
            response.message= UsernameRegistered;
        }else{
                repository.save(request);
                response.status=true;
                response.message= UserSuccess;
                response.id=request.getId();
        }
        return response;
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
