package it.univpm.ProgettoEsame.model;

import java.time.LocalDate;

public class Evento {
	private String nome;
	private String url;
	private String citta;
	private String stateCode;
	private LocalDate date;
	private String ora;
	private String genere;	
	private String stato;
    
	public Evento(String nome, String url, String citta, LocalDate date, String ora, String genere,String stato,String stateCode) {
		this.nome = nome;
		this.url = url;
		this.citta = citta;
		this.date = date;
		this.ora = ora;
		this.genere = genere;
		this.stato=stato;
		this.stateCode=stateCode;
	}

	public Evento() {};
	
	public Evento(String nome) {
		this.nome=nome;
	};
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
}
