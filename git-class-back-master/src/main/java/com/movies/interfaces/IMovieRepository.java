/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.movies.interfaces;

import com.movies.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 *
 * @author Andres
 */
public interface IMovieRepository extends MongoRepository<Movie, String> {
    @Query(value= "{title : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Movie> getMoviesByTitle(String title);

    @Query(value= "{gender : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Movie> getMoviesBygender(String gender);
}
