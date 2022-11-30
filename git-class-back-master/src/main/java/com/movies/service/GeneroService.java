/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.movies.service;

import com.movies.entities.Genero;
import com.movies.interfaces.IGeneroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres
 */
@Service
public class GeneroService {

    @Autowired
    IGeneroRepository repository;

    public Iterable<Genero> get() {
        Iterable<Genero> response = repository.findAll();
        return response;
    }

    public Genero create(Genero request) {

        return repository.save(request);

    }

    public Genero update(Genero genero) {
        Genero generoToUpdate = new Genero();
        if (repository.existsById(genero.getId())) {
            generoToUpdate = genero;
            repository.save(generoToUpdate);
        }
        return generoToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
