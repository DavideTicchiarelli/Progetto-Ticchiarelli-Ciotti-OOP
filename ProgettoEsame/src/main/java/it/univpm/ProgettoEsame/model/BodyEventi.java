package it.univpm.ProgettoEsame.model;

import java.util.Vector;

public class BodyEventi {
	
	private Vector<String>stati;
	private Vector<String>generi;
	private Vector<String>periodo;
	
	public BodyEventi(Vector<String> stati, Vector<String> generi, Vector<String> periodo) {
		this.stati = stati;
		this.generi = generi;
		this.periodo = periodo;
	}

	public Vector<String> getStati() {
		return stati;
	}

	public Vector<String> getGeneri() {
		return generi;
	}

	public Vector<String> getPeriodo() {
		return periodo;
	}

}
