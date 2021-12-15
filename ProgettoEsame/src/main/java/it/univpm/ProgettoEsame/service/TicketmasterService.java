package it.univpm.ProgettoEsame.service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Evento;
import it.univpm.ProgettoEsame.model.Stato;

public interface TicketmasterService {
	
	public abstract JSONObject toJSON(Vector<Evento> lista);
	public abstract JSONObject getJSONEvento(String stato);
	public abstract Vector<Evento> getEvento(JSONObject evento);
//	public abstract void salvaSuFile(JSONObject obj);
//	public abstract JSONObject getEventbyState(String state);
//	public abstract JSONArray getEventofromApi(String stato);
	Stato getStatoAPI(String stateCode);
	Stato getStatoEvents(String stateCode);


}
