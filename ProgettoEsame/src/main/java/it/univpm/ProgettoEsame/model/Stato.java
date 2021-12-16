package it.univpm.ProgettoEsame.model;

import java.util.Vector;

public class Stato {

	private String nome;
	private String stateCode;
	private Vector<Evento> evento=new Vector<Evento>();

	public Stato(String nome) {
		this.nome = nome;
		this.stateCode = null;
	}

	public Stato(String nome, String stateCode) {
		this.nome = nome;
		this.stateCode = stateCode;
		this.evento = null;
	}

	
	public Stato(String nome, String stateCode, Vector<Evento> evento) {
		this.nome = nome;
		this.stateCode = stateCode;
		this.evento = evento;
	}

	public Stato() {};
	
	public Vector<Evento> getEvento() {
		return evento;
	}

	public void setEvento(Vector<Evento> evento) {
		this.evento = evento;
	}

	public String getNomeStato() {
		return nome;
	}

	public void setNomeStato(String nome) {
		this.nome = nome;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
	this.stateCode = stateCode;
	}
	

}
