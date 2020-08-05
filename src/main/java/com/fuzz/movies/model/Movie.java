package com.fuzz.movies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String year;
    private String language;
    private String director;
    private String plot;
    private String runtime;
    private boolean isFavorite;

    @ElementCollection(targetClass = String.class)
    private List<String> actors;

}
