/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.dto.ResponseDto;
import com.movies.entities.Gender;
import com.movies.entities.Movie;
import com.movies.interfaces.IGenderRepository;
import com.movies.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
@Service
public class GenderService {

    @Autowired
    GenderRepository repository;

    public Iterable<Gender> get() {
        Iterable<Gender> response = repository.getAll();
        return response;
    }
    public Optional<Gender> getByName(String name) {
        Optional<Gender> response = repository.getGenderByName(name);
        return response;
    }

    public ResponseDto create(Gender request) {
        ResponseDto response = new ResponseDto();
        //Gender newGender = repository.save(request);
        List<Gender> gendersname = repository.getByName(request.getName());

        if (gendersname.size()>0){
            response.status=false;
            response.message="genero ya se encuentra creado";
        }else {
            repository.save(request);
            response.status = true;
            response.message = "El genero se creo de manera correcta";
            response.id = request.getId();

        }
        return response;
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
