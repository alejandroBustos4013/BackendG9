/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.repository;

import com.movies.entities.Gender;
import com.movies.interfaces.IGenderRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andres
 */
@Repository
public class GenderRepository {
    
    @Autowired
    IGenderRepository repository;
    
    public Iterable<Gender> getAll(){

        return repository.findAll();
    }
    
    public Optional<Gender> findById(String id){
        Optional<Gender> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){

        return repository.existsById(id);
    }
    
    public void deleteById(String id){

        repository.deleteById(id);
    }
    
    public Gender save(Gender gender){

        return repository.save(gender);
    }
    
}
