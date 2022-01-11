package it.univpm.ProgettoEsame.model;

import java.time.LocalDate;

/**
 * Classe che descrive l'evento.
 *
 */
public class Evento {
	/**
	 * Variabile che descrive il nome dell'evento.
	 */
	private String nome;
	
	/**
	 * Variabile che descrive l'url dell'evento.
	 */
	private String url;
	
	/**
	 * Variabile che descrive la città dell'evento.
	 */
	private String citta;
	
	/**
	 * Variabile che descrive lo stateCode dello stato dove si svolge l'evento.
	 */
	private String stateCode;
	
	/**
	 * Variabile che descrive la data dell'evento.
	 */
	private LocalDate date;
	
	/**
	 * Variabile che descrive l'ora dell'evento.
	 */
	private String ora;
	
	/**
	 * Variabile che descrive il genere dell'evento.
	 */
	private String genere;	
	
	/**
	 * Variabile che descrive lo stato dove si svolge l'evento.
	 */
	private String stato;
    
	/**
	 * Costruttore che inizializza l'oggetto evento.
	 * @param nome
	 * @param url
	 * @param citta
	 * @param date
	 * @param ora
	 * @param genere
	 * @param stato
	 * @param stateCode
	 */
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

	/**
	 * Costruttore dell'oggetto Evento (vuoto).
	 */
	public Evento() {};
	
	/**
	 * Costruttore dell'oggetto Evento.
	 * @param nome.
	 */
	public Evento(String nome) {
		this.nome=nome;
	};

	/**
	 * Getter dell'attributo nome.
	 * @return nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setter dell'attributo nome.
	 * @param nome Parametro del Setter.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Getter dell'attributo url.
	 * @return url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter dell'attributo url.
	 * @param url Parametro del Setter.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter dell'attributo citta.
	 * @return citta.
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Setter dell'attributo citta.
	 * @param citta Parametro del Setter.
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * Getter dell'attributo date.
	 * @return date.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Setter dell'attributo date.
	 * @param date Parametro del Setter.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Getter dell'attributo ora.
	 * @return ora.
	 */
	public String getOra() {
		return ora;
	}

	/**
	 * Setter dell'attributo ora.
	 * @param ora Parametro del Setter.
	 */
	public void setOra(String ora) {
		this.ora = ora;
	}

	/**
	 * Getter dell'attributo genere.
	 * @return genere.
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Setter dell'attributo genere.
	 * @param genere Parametro del Setter.
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Getter dell'attributo stato.
	 * @return stato.
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Setter dell'attributo stato.
	 * @param stato Parametro del Setter.
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Getter dell'attributo statecode.
	 * @return stateCode.
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * Setter dell'attributo stateCode.
	 * @param stateCode Parametro del Setter.
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
}
