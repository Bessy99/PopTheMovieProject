package com.bessy.PopTheMovieAPI.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bessy.PopTheMovieAPI.Model.Utente;

@Repository
public interface UserRepository extends CrudRepository<Utente, Long>{
	
}
