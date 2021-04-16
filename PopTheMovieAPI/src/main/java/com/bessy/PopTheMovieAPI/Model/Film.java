package com.bessy.PopTheMovieAPI.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film {
	@Id
	private long id;
	private String titolo;
	private String categoria;
	
	@OneToMany(mappedBy = "film", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FilmVisti> filmVisti = new HashSet<>();
	
	@OneToMany(mappedBy = "film", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FilmDaVedere> filmDaVedere = new HashSet<>();
	
	public Film() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Film(long id, String titolo, String categoria) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.categoria = categoria;
	}
	
	
}