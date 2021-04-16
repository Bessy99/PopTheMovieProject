package com.bessy.PopTheMovieAPI.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bessy.PopTheMovieAPI.Model.Film;
import com.bessy.PopTheMovieAPI.Model.FilmDaVedere;
import com.bessy.PopTheMovieAPI.Repository.FilmDaVedereRepository;
import com.bessy.PopTheMovieAPI.Repository.FilmRepository;



@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    
    @Autowired
    private FilmDaVedereRepository filmDaVedereRepository;
        
    @GetMapping
    public Iterable<Film> findAllFilms() {
    	return filmRepository.findAll();
    }
    
    @GetMapping("/filmDaVedere")
    public Iterable<FilmDaVedere> findAllFilmDaVedere() {
    	return filmDaVedereRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> findFilmById(@PathVariable(value = "id") long id) {
    	Optional<Film> film = filmRepository.findById(id);

        if(film.isPresent()) {
            return ResponseEntity.ok().body(film.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/filmDaVedere/{id}")
    public ResponseEntity<FilmDaVedere> findFilmDaVedereById(@PathVariable(value = "id") long id) {
    	Optional<FilmDaVedere> filmDaVedere = filmDaVedereRepository.findById(id);

        if(filmDaVedere.isPresent()) {
        	return ResponseEntity.ok().body(filmDaVedere.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public Film saveFilm(@Validated @RequestBody Film film) {
		return filmRepository.save(film);
    }
    
}