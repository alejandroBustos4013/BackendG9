/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.movies.interfaces;

import com.movies.entities.Movie;
import com.movies.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andres
 */
public interface IUserRepository extends MongoRepository<User, String> {

    @Query(value= "{username : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<User> getUserByUsername(String username);

    @Query(value= "{email : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<User> getUserByEmail(String email);

    @Query(value= "{email : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    Optional<User> findUserByEmail(String email);

    @Query(value= "{username : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    Optional<User> findUserByUsername(String username);

    @Query(value= "{full_name : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<User> getUserByName(String full_name);

    @Query(value= "{number_identification : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<User> getUserByCC(String number_identification);
    
}
