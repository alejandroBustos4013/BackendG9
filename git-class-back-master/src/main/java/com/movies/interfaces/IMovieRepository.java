/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.movies.interfaces;

import com.movies.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
public interface IMovieRepository extends MongoRepository<Movie, String> {
    @Query(value= "{title : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Movie> getMoviesByTitle(String title);// esta creado antes de la intervencion FUNCIONANDO

    //@Query(value= "{genders:{$elemMatch:{name: ?0}}}")
    //Iterable<Movie> getMoviesByGender(String name);// esta creado antes de la intervencion FUNCIONANDO

    @Query(value= "{'genders.name': ?0}")
    Iterable<Movie> getMoviesByGender(String name);

    //@Query (value = "{users:{$elemMatch:{id: ?0}}}")
    //List<Movie> getMovieByUserId(String id);



}
