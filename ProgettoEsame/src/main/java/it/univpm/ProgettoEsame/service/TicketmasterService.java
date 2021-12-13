package it.univpm.ProgettoEsame.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.ProgettoEsame.model.Stato;

public interface TicketmasterService {
	
	public abstract JSONObject toJSON(Stato stato);
	public abstract JSONObject getJSONEvento(String stato);
	public abstract Stato getEvento(JSONObject evento);
//	public abstract void salvaSuFile(JSONObject obj);
//	public abstract JSONObject getEventbyState(String state);
//	public abstract JSONArray getEventofromApi(String stato);


}
