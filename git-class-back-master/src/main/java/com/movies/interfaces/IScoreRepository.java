/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.movies.interfaces;

import com.movies.entities.Movie;
import com.movies.entities.Score;
import com.movies.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 *
 * @author Andres
 */
public interface IScoreRepository extends MongoRepository<Score, String> {

    @Query(value= "{movies : ?0, users: ?1}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Score> getScoreByMoviesAndUser(Movie movies, User users);

    @Query(value= "{movies._id:{$movie:ObjectId(?0)}, users._id:{$user:ObjectId(?1)}}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Score> getScoreByMoviesAndUserById(String movieId, String userId);


}
