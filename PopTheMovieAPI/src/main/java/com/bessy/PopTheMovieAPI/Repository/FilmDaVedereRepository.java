package com.bessy.PopTheMovieAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.FilmDaVedere;


@Repository
public interface FilmDaVedereRepository extends CrudRepository<FilmDaVedere, Long>{
	
}