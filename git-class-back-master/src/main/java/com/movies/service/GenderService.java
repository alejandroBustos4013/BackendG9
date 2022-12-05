/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Gender;
import com.movies.interfaces.IGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@Service
public class GenderService {

    @Autowired
    IGenderRepository repository;

    public Iterable<Gender> get() {
        Iterable<Gender> response = repository.findAll();
        return response;
    }

    public ResponseDto create(Gender request) {

        Gender newGender = repository.save(request);
        ResponseDto responseDto = new ResponseDto();
        responseDto.status = true;
        responseDto.message = "El genero se creo de manera correcta";
        responseDto.id = newGender.getId();
        return responseDto;
    }

    public Gender update(Gender gender) {
        Gender genderToUpdate = new Gender();
        if (repository.existsById(gender.getId())) {
            genderToUpdate = gender;
            repository.save(genderToUpdate);
        }
        return genderToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
