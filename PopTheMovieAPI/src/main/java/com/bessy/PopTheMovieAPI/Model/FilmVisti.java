package com.bessy.PopTheMovieAPI.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "filmvisti")
public class FilmVisti {
	
	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "Utente_id")
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name = "Film_id")
	private Film film;

	public FilmVisti() {}
	
	public FilmVisti(long id, Utente utente, Film film) {
		super();
		this.id = id;
		this.utente = utente;
		this.film = film;
	}



	public long getId() {
		return id;
	}

	public Utente getUtente() {
		return utente;
	}

	public Film getFilm() {
		return film;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	
}
