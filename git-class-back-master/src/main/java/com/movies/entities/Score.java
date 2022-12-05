package com.movies.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Document("score")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Integer score;
    private Movie movie;
    private User users;


}
