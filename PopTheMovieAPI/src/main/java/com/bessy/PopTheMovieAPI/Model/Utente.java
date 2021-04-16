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
@Table(name = "utente")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy = "utente", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FilmVisti> filmVisti = new HashSet<>();
	
	@OneToMany(mappedBy = "utente", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FilmDaVedere> filmDaVedere = new HashSet<>();
	
	
	//costruttori, getter e setter
	
	public Utente() {
		super();
	}

	public Utente(long id, String email, String password, String nome, String cognome) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}



	public String getNome() {
		return nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public long getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
