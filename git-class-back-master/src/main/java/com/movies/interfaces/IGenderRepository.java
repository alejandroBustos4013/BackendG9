/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.movies.interfaces;


import com.movies.entities.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Andres
 */
public interface IGenderRepository extends MongoRepository<Gender, String> {
    
}
