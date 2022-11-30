/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.repository;

import com.movies.entities.Genero;
import com.movies.interfaces.IGeneroRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andres
 */
@Repository
public class GeneroRepository {
    
    @Autowired
    IGeneroRepository repository;
    
    public Iterable<Genero> getAll(){
        return repository.findAll();
    }
    
    public Optional<Genero> findById(String id){
        Optional<Genero> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Genero save(Genero genero){
        return repository.save(genero);
    }
    
}
