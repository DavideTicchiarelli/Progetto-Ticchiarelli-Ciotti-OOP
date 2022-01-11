package it.univpm.ProgettoEsame.service;


import java.util.Vector;

import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.exceptions.EventiException;
import it.univpm.ProgettoEsame.model.Evento;

/**
 *Interfaccia di TicketmasterServiceImpl che contiene i metodi richiamati dal Controller
 *
 */
public interface TicketmasterService {
	
	public abstract JSONObject getJSONEventoStato(String stateCode);
	public abstract Vector<Evento> getStatoEvents(String stateCode) throws EventiException;
	public abstract JSONObject toJSON(Vector<Evento> stato);
	public abstract JSONObject getResultEventi(String statecode,String genere,String inizio,String fine) throws EventiException;



}
