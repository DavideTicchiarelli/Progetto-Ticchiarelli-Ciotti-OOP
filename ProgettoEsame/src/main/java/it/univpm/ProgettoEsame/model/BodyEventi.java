package it.univpm.ProgettoEsame.model;

import java.util.Vector;

/**
 * 
 * Classe che descrive la struttura del Body degli eventi per la chiamata.
 *
 */
public class BodyEventi {
	
	/**
	 * Vettore di StateCode.
	 */
	private Vector<String>stateCode;
	
	/**
	 * Vettore di generi.
	 */
	private Vector<String>generi;
	
	/**
	 * Vettore di date del periodo personalizzato.
	 */
	private Vector<String>periodo;
	
	/**
	 * Costruttore della classe BodyEventi
	 * @param stateCode Vettore contenente gli Statecode degli stati.
	 * @param generi    Vettore contenente i generi.
	 * @param periodo   Vettore contenente le date del periodo personalizzato.
	 */
	public BodyEventi(Vector<String> stateCode, Vector<String> generi, Vector<String> periodo) {
		this.stateCode = stateCode;
		this.generi = generi;
		this.periodo = periodo;
	}
	
	/**
	 * Getter dello vettore StateCode
	 * @return stateCode 
	 */
	public Vector<String> getStati() {
		return stateCode;
	}

	/**
	 * Getter del vettore Generi
	 * @return generi
	 */
	public Vector<String> getGeneri() {
		return generi;
	}

	/**
	 * Getter del vettore Periodo
	 * @return periodo 
	 */
	public Vector<String> getPeriodo() {
		return periodo;
	}
    
	/**
     * Setter del vettore StateCode
     * @param stateCode Parametro del Setter
     */
	public void setStateCode(Vector<String> stateCode) {
		this.stateCode = stateCode;
	}

	/**
     * Setter del vettore Generi
     * @param generi Parametro del Setter
     */
	public void setGeneri(Vector<String> generi) {
		this.generi = generi;
	}

	/**
     * Setter del vettore Periodo
     * @param periodo Parametro del Setter
     */
	public void setPeriodo(Vector<String> periodo) {
		this.periodo = periodo;
	}
	

}
