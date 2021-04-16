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

import com.bessy.PopTheMovieAPI.Model.Utente;
import com.bessy.PopTheMovieAPI.Repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public Iterable<Utente> findAllUsers() {
    	return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> findUserById(@PathVariable(value = "id") long id) {
    	Optional<Utente> user = userRepository.findById(id);

        if(user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Utente saveUser(@Validated @RequestBody Utente user) {
		return userRepository.save(user);
    }
    
}