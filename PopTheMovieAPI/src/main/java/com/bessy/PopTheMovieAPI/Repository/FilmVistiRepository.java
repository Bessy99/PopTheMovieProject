package com.bessy.PopTheMovieAPI.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.FilmVisti;

@Repository
public interface FilmVistiRepository extends CrudRepository<FilmVisti, Long>{
	
}
